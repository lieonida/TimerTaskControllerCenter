package cn.com.voc.ControlCenter.TimerTask.controller;

import cn.com.voc.ControlCenter.TimerTask.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import cn.com.voc.ControlCenter.TimerTask.entity.Task;
import cn.com.voc.ControlCenter.TimerTask.service.TaskService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @description: 热为奴控制器
 * @author: hn
 * @date: 2019/4/6
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private static final Logger log = Logger.getLogger("TaskController.class");

    @Autowired
    private TaskService taskService;

    @Autowired
    @Qualifier("sch")
    private Scheduler scheduler;

    @ApiOperation(value="创建任务",notes = "创建任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "cronExpression", value ="Cron表达式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "taskDescription", value = "任务描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "schedulePath", value ="调度路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目Id", required = false, dataType = "String"),
            @ApiImplicitParam(name = "createTime", value ="创建时间", required = false, dataType = "String"),
    })
    @PostMapping
    @ResponseBody
    public String insert(@RequestParam(value = "taskName") String taskName,
         @RequestParam(value = "cronExpression") String  cronExPression,
         @RequestParam(value = "taskDescription") String taskDescription,
         @RequestParam(value = "schedulePath") String schedulePath,
         @RequestParam(value = "projectId",required = false) String projectId,
         @RequestParam(value = "createTime",required = false) String createTime) throws Exception
    {
        Task task = new Task();
        String taskId = StringUtil.getUUID();
        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setCronExpression(cronExPression);
        task.setTaskDescription(taskDescription);
        task.setSchedulePath(schedulePath);
        task.setStatus('0');
        task.setProjectId(projectId);
        task.setCreateTime(new Date());
        taskService.insertTask(task);
        return taskId;
    }

    @ApiOperation(value="查询任务列表",notes = "根据项目查询所有的任务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value ="页面容量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value ="当前页面", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "projectId", value = "项目Id", required = false, dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "taskDescription", value = "任务描述", required = false, dataType = "String"),
    })
    @GetMapping
    @ResponseBody
    public Map<String,Object> queryALLTask(@RequestParam(value="pageSize",required = false) Integer pageSize,
                                   @RequestParam(value="pageNum",required = false) Integer pageNum,
                                           @RequestParam(value="projectId",required = false) String projectId,
                                           @RequestParam(value="taskName",required = false) String taskName,
                                           @RequestParam(value="taskDescription",required = false) String taskDescription
                                    ){
        Integer index = pageNum!=null?pageNum:1;
        Integer limit = pageSize!=null?pageSize:10;
        Map<String,String> map = new HashMap<String, String>();
        if(projectId!=null && !projectId.equals("")){
            map.put("projectId",projectId);
        }
        if(taskName!=null && !taskName.equals("")){
            map.put("taskName",taskName);
        }
        if(taskDescription!=null && !taskDescription.equals("")){
            map.put("taskDescription",taskDescription);
        }
        return taskService.selectAllTask(limit,index,map);

        //return tasks;
    }

    @ApiOperation(value="删除任务",notes = "删除任务信息")
    @ApiImplicitParam(name = "taskId",value = "任务Id",required = false,dataType = "String")
    @DeleteMapping
    public void deleteTask(@RequestParam(value = "taskId") String taskId){
        taskService.deleteTask(taskId);
    }

    @ApiOperation(value="编辑任务",notes = "更新任务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value ="任务Id", required = false, dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "taskDescription", value = "任务描述", required = false, dataType = "String"),
            @ApiImplicitParam(name = "cronExpression", value ="Cron表达式", required = false, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目Id", required = false, dataType = "String"),
            @ApiImplicitParam(name = "schedulePath", value ="调度路径", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value ="状态", required = false, dataType = "String")
    })
    @PutMapping
    public void updateTask(@RequestParam(value="taskId") String taskId,
                           @RequestParam(value="taskName",required = false) String taskName,
                           @RequestParam(value="taskDescription",required = false) String taskDescription,
                           @RequestParam(value="cronExpression",required = false)String  cronExpression,
                           @RequestParam(value="projectId",required = false) String projectId,
                           @RequestParam(value="schedulePath",required = false) String schedulePath,
                           @RequestParam(value="status",required = false) String status
    ) throws Exception{
        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName(taskName);
        if(status!=null){
            task.setStatus(status.charAt(0));
        }
        if(taskDescription != null){
            task.setTaskDescription(taskDescription);
        }
        task.setCronExpression(cronExpression);
        task.setProjectId(projectId);
        task.setSchedulePath(schedulePath);
        taskService.upeateTask(task);
        if(cronExpression != null){
            String path = taskService.selectTask(taskId).getSchedulePath();
            String str[] = path.split("/");
            String classPath = str[str.length-2];
            String methodPath = str[str.length-1];
            log.info("/"+classPath+"/"+methodPath);
            JobKey jobKey = new JobKey("/"+classPath+"/"+methodPath);
            // 获取 jobDetail
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            // 生成 trigger
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .build();
            // 删除任务，不删除会报错。报任务已存在
            scheduler.deleteJob(jobKey);
            // 启动任务
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    @ApiOperation(value="查询任务详情",notes = "根据id查询任务")
    @ApiImplicitParam(name = "taskId",value = "任务Id",required = true,dataType = "String")
    @GetMapping("/taskDetail")
    @ResponseBody
    public Task selectTask(@RequestParam(value="taskId") String taskId){
        Task task = taskService.selectTask(taskId);
        return task;
    }
}

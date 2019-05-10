package cn.com.voc.ControlCenter.TimerTask.common;

import cn.com.voc.ControlCenter.TimerTask.entity.Log;
import cn.com.voc.ControlCenter.TimerTask.entity.Task;
import cn.com.voc.ControlCenter.TimerTask.service.LogService;
import cn.com.voc.ControlCenter.TimerTask.service.TaskService;
import cn.com.voc.ControlCenter.TimerTask.util.StringUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.logging.Logger;

@Configuration
@Component
public class JobGetAll {

    private static final Logger log = Logger.getLogger("JobGetAll");

    @Autowired
    private LogService logService;

    @Autowired
    private TaskService taskService;

    @Autowired
    @Qualifier("sch")
    private Scheduler scheduler;

    public void printLine() throws Exception{
        String path = "http://localhost:8080/action/line";
        Task task = getTask(path);
        if(task != null) {
            if (task.getStatus() == '1') {
                log.info("现在开始画横线！");
                log.info("横线已经画完！");
                insertLog(task);
            }
        }
    }

    public void printSquare(){
        String path ="http://localhost:8080/action/square";
        Task task = taskService.selectTaskByPath(path);
        if(task != null) {
            if (task.getStatus() == '1') {
                log.info("现在开始画四边形！");
                log.info("四边形已经画好！");
                insertLog(task);
            }
        }
    }

    public void printCircle() throws Exception{
        String path = "http://localhost:8080/action/circle";
        Task task = taskService.selectTaskByPath(path);
        if(task != null) {
            if (task.getStatus() == '1') {
                log.info("现在开始画圆！");
                log.info("圆形已经画好！");
                insertLog(task);
            }
        }
    }

    public void printHeart(){
        String path = "http://localhost:8080/action/heart";
        Task task = taskService.selectTaskByPath(path);
        if(task != null) {
            if (task.getStatus() == '1') {
                log.info("现在开始画心！");
                log.info("心形已经画好！");
                insertLog(task);
            }
        }
    }

    /**
     * @description 根据调度路径获取任务详情
     * @author hn
     * @date 19:41 2019/4/6
     * @Param path
     * @return Task
     */
    public Task  getTask(String path){
        if(path != null && !path.equals("")){
            Task task = taskService.selectTaskByPath(path);
            if(task != null) {
                return task;
            }
        }
        return null;
    }

    /**
     * @description 为任务插入日志
     * @author hn
     * @date 19:42 2019/4/6
     * @Param log
     * @return
     */
    public void insertLog(Task task){
        Date createTime = new Date();
        String logDescription ="任务执行成功，msg[SchduleJob(id="+task.getProject().getProjectId()+"，projectName="+task.getProject().getProjectName()
                +",jobName="+task.getTaskName()+",cronExpression="+task.getCronExpression()+",description="+task.getTaskDescription()+",ulr="+
                task.getSchedulePath()+",createTime="+task.getCreateTime()+")]";
        Log log = new Log();
        log.setLogId(StringUtil.getUUID());
        log.setStatus(task.getStatus());
        log.setProjectId(task.getProject().getProjectId());
        log.setLogDescription(logDescription);
        log.setTaskId(task.getTaskId());
        log.setCreateTime(createTime);
        logService.insertLog(log);
    }
}

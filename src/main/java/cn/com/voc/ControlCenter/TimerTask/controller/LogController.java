package cn.com.voc.ControlCenter.TimerTask.controller;

import cn.com.voc.ControlCenter.TimerTask.entity.Log;
import cn.com.voc.ControlCenter.TimerTask.service.LogService;
import cn.com.voc.ControlCenter.TimerTask.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/log")
public class LogController {

    private static final Logger log = Logger.getLogger("LogContrller.class");

    @Autowired
    private LogService logService;

    @ApiOperation(value="创建日志",notes="创建日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId",value ="任务Id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "status",value ="启动状态",required = true,dataType = "char"),
            @ApiImplicitParam(name = "taskId",value ="日志描述",required = true,dataType = "String"),
            @ApiImplicitParam(name = "taskId",value ="创建时间",required = true,dataType = "Date")
    })
    @PostMapping
    @ResponseBody
    public String insert(@RequestParam(value ="taskId") String taskId,
                         @RequestParam(value ="status",required = false) char status,
                         @RequestParam(value ="logDescription",required = false) String logDescription,
                         @RequestParam(value ="createTime",required = false) Date createTime){
        Log log = new Log();
        log.setLogId(StringUtil.getUUID());
        log.setTaskId(taskId);
        log.setLogDescription(logDescription);
        log.setCreateTime(new Date());
        logService.insertLog(log);
        return "";
    }

    @ApiOperation(value="查询日志信息列表",notes = "根据任务查询所有日志信息")
    @ApiImplicitParam(name = "taskId",value = "任务Id",required = true,dataType = "String")
    @GetMapping
    @ResponseBody
    public List<Log> select(@RequestParam(value = "taskId") String taskId){
        List<Log> logs = logService.queryAllLog(taskId);
        return logs;
    }

//    @ApiOperation(value="删除日志信息",notes = "根据任务删除所有日志信息")
//    @ApiImplicitParam(name = "taskId",value = "任务Id",required = true,dataType = "String")
//    @DeleteMapping
//    @ResponseBody
//    public String delete(@RequestParam(value="taskId") String taskId){
//        logService.deleteLog(taskId);
//        return "";
//    }
}

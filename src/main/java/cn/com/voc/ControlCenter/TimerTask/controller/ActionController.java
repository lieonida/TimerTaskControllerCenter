package cn.com.voc.ControlCenter.TimerTask.controller;

import cn.com.voc.ControlCenter.TimerTask.service.LogService;
import cn.com.voc.ControlCenter.TimerTask.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * @description: 具体任务执行类
 * @author: hn
 * @date: 2019/4/4
 */
@RestController
@RequestMapping("/action")
@EnableScheduling
public class ActionController {

    private static final Logger log = Logger.getLogger("ActionController.class");

    @Autowired
    @Qualifier("sch")
    private Scheduler scheduler;

    @ApiOperation(value="画横线",notes="在后台画一条横线")
    @GetMapping("/line")
    public void printLine(){
        String jobName="/action/line";
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
           log.info("运行定时任务出错："+e.getMessage());
        }
    }

    @ApiOperation(value="画四边形",notes="在后台画一个四边形")
    @GetMapping("/square")
    public void printSquare(){
        String jobName="/action/square";
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.info("运行定时任务出错："+e.getMessage());
        }
    }

    @ApiOperation(value ="画圆",notes = "在后台画一个圆形")
    @GetMapping("/circle")
    public void printCircle(){
        String jobName="/action/circle";
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.info("运行定时任务出错："+e.getMessage());
        }
    }

    @ApiOperation(value="画心形",notes="在后台画一个心形")
    @GetMapping("/heart")
    public void printHeart(){
        String jobName="/action/heart";
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.info("运行定时任务出错："+e.getMessage());
        }
    }
}

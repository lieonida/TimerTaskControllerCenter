package cn.com.voc.ControlCenter.TimerTask.config;

import cn.com.voc.ControlCenter.TimerTask.common.JobGetAll;
import cn.com.voc.ControlCenter.TimerTask.entity.Task;
import cn.com.voc.ControlCenter.TimerTask.mapper.TaskMapper;
import cn.com.voc.ControlCenter.TimerTask.service.LogService;
import cn.com.voc.ControlCenter.TimerTask.service.TaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Date;

@Configuration
public class QuartzConfig {

    @Autowired
    private TaskService taskService;

    @Autowired
    private LogService logService;

    @Autowired
    private TaskMapper taskMapper;

    @Bean(name = "/action/line")
    public MethodInvokingJobDetailFactoryBean jobLine(JobGetAll jobGetAll) throws  Exception{

        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        factoryBean.setConcurrent(true);
        // 使用哪个对象
        factoryBean.setTargetObject(jobGetAll);
        // 使用哪个方法
        factoryBean.setTargetMethod("printLine");
        return  factoryBean;
    }

    // 定义什么时候做，使用 cron 表达式
    @Bean(name = "cron1")
    public CronTriggerFactoryBean cronLine(@Qualifier("/action/line")MethodInvokingJobDetailFactoryBean jobLine){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置job对象
        factoryBean.setJobDetail( jobLine.getObject() );
        // 设置执行时间
        factoryBean.setCronExpression("0/30 * * * * ?");
        return  factoryBean;
    }

    @Bean(name = "/action/square")
    public MethodInvokingJobDetailFactoryBean jobSquare(JobGetAll jobGetAll){

        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        factoryBean.setConcurrent(true);
        // 使用哪个对象
        factoryBean.setTargetObject(jobGetAll);
        // 使用哪个方法
        factoryBean.setTargetMethod("printSquare");
        return  factoryBean;
    }

    // 定义什么时候做，使用 cron 表达式
    @Bean(name = "cron2")
    public CronTriggerFactoryBean cronSquare(@Qualifier("/action/square")MethodInvokingJobDetailFactoryBean jobSquare){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置job对象
        factoryBean.setJobDetail( jobSquare.getObject() );
        // 设置执行时间
        factoryBean.setCronExpression("0/30 * * * * ?");
        return  factoryBean;
    }

    @Bean(name = "/action/circle")
    public MethodInvokingJobDetailFactoryBean jobCircle(JobGetAll jobGetAll){

        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        factoryBean.setConcurrent(true);
        // 使用哪个对象
        factoryBean.setTargetObject(jobGetAll);
        // 使用哪个方法
        factoryBean.setTargetMethod("printCircle");
        return  factoryBean;
    }

     //定义什么时候做，使用 cron 表达式
    @Bean(name = "cron3")
    public CronTriggerFactoryBean cronCircle(@Qualifier("/action/circle")MethodInvokingJobDetailFactoryBean jobCircle){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置job对象
        factoryBean.setJobDetail( jobCircle.getObject() );
        // 设置执行时间
        factoryBean.setCronExpression("0/30 * * * * ?");
        return  factoryBean;
    }

    @Bean(name = "/action/heart")
    public MethodInvokingJobDetailFactoryBean jobHeart(JobGetAll jobGetAll){

        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        factoryBean.setConcurrent(true);
        // 使用哪个对象
        factoryBean.setTargetObject(jobGetAll);
        // 使用哪个方法
        factoryBean.setTargetMethod("printHeart");
        return  factoryBean;
    }

    //定义什么时候做，使用 cron 表达式
    @Bean(name = "cron4")
    public CronTriggerFactoryBean cronHeart(@Qualifier("/action/heart")MethodInvokingJobDetailFactoryBean jobHeart){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置job对象
        factoryBean.setJobDetail( jobHeart.getObject() );
        // 设置执行时间
        factoryBean.setCronExpression("0/30 * * * * ?");
        return  factoryBean;
    }

   @Bean(name = "sch")
    public SchedulerFactoryBean scheduler1(Trigger... triggers){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        // 设置 triggers
        factoryBean.setTriggers( triggers );
        // 自动运行
        factoryBean.setAutoStartup(true);
        return factoryBean;
    }

}

package cn.com.voc.ControlCenter.TimerTask.util;

import cn.com.voc.ControlCenter.TimerTask.common.QuartzBean;
import org.quartz.*;

public class QuartzUtil {

    public static void updateScheduleJob(Scheduler scheduler, QuartzBean quartzBean)  {
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzBean.getJobName());
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务出错："+e.getMessage());
        }
    }

}

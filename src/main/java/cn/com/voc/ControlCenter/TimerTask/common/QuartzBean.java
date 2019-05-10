package cn.com.voc.ControlCenter.TimerTask.common;

public class QuartzBean {

    /** 任务id */
    private String  id;

    /** 任务名称 */
    private String jobName;

    /** 任务执行类 */
    private String jobClass;

    /** 任务状态 启动还是暂停*/
    private Integer status;

    /** 任务运行时间表达式 */
    private String cronExpression;

    public String getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobClass() {
        return jobClass;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}

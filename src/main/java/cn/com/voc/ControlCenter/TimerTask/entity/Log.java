package cn.com.voc.ControlCenter.TimerTask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 日志信息实体
 * @author: hn
 * @date: 2019/4/4
 */
public class Log implements Serializable {

    //日志id
    private String logId;

    //项目id
    private String projectId;

    //调度状态
    private char status;

    //日志描述
    private String logDescription;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    //任务Id
    private String taskId;

    //任务
    private Task task;

    //项目
    private Project project;

    public String getLogId() {
        return logId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public char getStatus() {
        return status;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Task getTask() {
        return task;
    }

    public Project getProject() {
        return project;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setLogDescription(String logDesription) {
        this.logDescription = logDesription;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

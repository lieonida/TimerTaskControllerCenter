package cn.com.voc.ControlCenter.TimerTask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 任务信息实体
 * @author: hn
 * @date: 2019/4/4
 */
public class Task implements Serializable {

    //任务id
    private String taskId;

    //任务名称
    private String taskName;

    //cron表达式
    private String cronExpression;

    //任务描述
    private String taskDescription;

    //调度路径
    private String schedulePath;

    //启动状态
    private char status;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String projectId;

    private Project project;

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getSchedulePath() {
        return schedulePath;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public char getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Project getProject() {
        return project;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setSchedulePath(String schedulePath) {
        this.schedulePath = schedulePath;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

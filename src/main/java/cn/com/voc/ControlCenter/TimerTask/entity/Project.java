package cn.com.voc.ControlCenter.TimerTask.entity;

import java.io.Serializable;

/**
 * @description: 项目信息实体
 * @author: hn
 * @date: 2019/4/4
 */
public class Project implements Serializable {

    //项目id
    private String projectId;

    //项目名称
    private String projectName;

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

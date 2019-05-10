package cn.com.voc.ControlCenter.TimerTask.service;

import cn.com.voc.ControlCenter.TimerTask.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.voc.ControlCenter.TimerTask.entity.Project;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @description: 项目信息服务类
 * @author: hn
 * @date: 2019/4/4
 */
public interface ProjectService {

   /**
    * @description 插入项目信息
    * @author hn
    * @date 20:36 2019/4/4
    * @Param project
    * @return boolean
    */
    boolean insertProject(Project project);

    /**
     * @description 查询所有项目信息
     * @author hn
     * @date 20:36 2019/4/4
     * @Param
     * @return List<Project>
     */
    List<Project> selectProject();


}

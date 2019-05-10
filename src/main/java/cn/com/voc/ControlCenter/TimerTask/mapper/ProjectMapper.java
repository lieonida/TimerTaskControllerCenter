package cn.com.voc.ControlCenter.TimerTask.mapper;

import cn.com.voc.ControlCenter.TimerTask.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 项目Dao层接口
 * @author: hn
 * @date: 2019/4/4
 */
@Component(value = "ProjectMapper")
@Mapper
public interface ProjectMapper {

    /**
     * @description 插入项目信息
     * @author hn
     * @date 20:00 2019/4/4
     * @Param project
     * @return Project
     */
   int insert(Project project);

   /**
    * @description 查询所有项目信息
    * @author hn
    * @date 20:01 2019/4/4
    * @Param
    * @return List<Project>
    */
   List<Project> select();

   /**
    * @description 根据项目id查询项目信息
    * @author hn
    * @date 20:02 2019/4/4
    * @Param projectId
    * @return Project
    */
   Project findByProjectId(String projectId);

}

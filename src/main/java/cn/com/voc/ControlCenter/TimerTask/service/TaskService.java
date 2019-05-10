package cn.com.voc.ControlCenter.TimerTask.service;

import cn.com.voc.ControlCenter.TimerTask.entity.Task;
import java.util.Map;

/**
 * @description:任务服务类
 * @author: hn
 * @date: 2019/4/4
 */
public interface TaskService {

    /**
     * @description 插入任务信息
     * @author hn
     * @date 20:51 2019/4/4
     * @Param task
     * @return boolean
     */
    boolean insertTask(Task task);

    /**
     * @description 根据查询条件(projectId,taskName,taskDescription)查询任务列表
     * @author hn
     * @date 20:52 2019/4/4
     * @Param pageSize,pageNum,map
     * @return Map<String,Object>
     */
    Map<String,Object> selectAllTask(Integer pageSize,Integer PageNum,Map<String,String> map);
    
    /**
     * @description 删除任务
     * @author hn
     * @date 20:53 2019/4/4
     * @Param taskId
     * @return boolean
     */
    boolean deleteTask(String taskId);

    /**
     * @description 更新任务信息
     * @author hn
     * @date 20:55 2019/4/4
     * @Param task
     * @return boolean
     */
    boolean upeateTask(Task task);

    /**
     * @description 根据任务id查询任务
     * @author hn
     * @date 20:55 2019/4/4
     * @Param taskId
     * @return Task
     */
    Task selectTask(String taskId);

    /**
     * @description 根据调度路径查询任务
     * @author hn
     * @date 19:46 2019/4/6
     * @Param schedulePath
     * @return Task
     */
    Task selectTaskByPath(String schedulePath);
}

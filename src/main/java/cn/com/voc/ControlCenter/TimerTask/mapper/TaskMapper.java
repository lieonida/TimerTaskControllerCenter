package cn.com.voc.ControlCenter.TimerTask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import cn.com.voc.ControlCenter.TimerTask.entity.Task;
import java.util.List;
import java.util.Map;

/**
 * @description: 任务Dao层接口
 * @author: hn
 * @date: 2019/4/4
 */
@Component(value = "TaskMapper")
@Mapper
public interface TaskMapper {

    /**
     * @description 插入任务信息
     * @author hn
     * @date 20:04 2019/4/4
     * @Param task
     * @return int
     */
    int insert(Task task);

    /**
     * @description 删除任务
     * @author hn
     * @date 20:04 2019/4/4
     * @Param taskId
     * @return int
     */
    int deleteByTaskId(String taskId);

    /**
     * @description 修改任务信息
     * @author hn
     * @date 20:05 2019/4/4
     * @Param task
     * @retur int
     */
    int updateByTaskId(Task task);

    /**
     * @description 根据查询条件查询任务信息
     * @author hn
     * @date 20:05 2019/4/4
     * @Param map
     * @return List<Task>
     */
    List<Task> selectAllTasks(Map<String,String> map);

    /**
     * @description 根据任务id查询任务信息
     * @author hn
     * @date 20:07 2019/4/4
     * @Param taskId
     * @return Task
     */
    Task findByTaskId(String taskId);

    /**
     * @description 根据调度路径查询任务详情
     * @author hn
     * @date 19:44 2019/4/6
     * @Param
     * @return
     */
    Task findBySchedulePath(String schedulePath);
}

package cn.com.voc.ControlCenter.TimerTask.mapper;

import cn.com.voc.ControlCenter.TimerTask.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 日志信息Dao层接口
 * @author: hn
 * @date: 2019/4/4
 */
@Component(value = "LogMapper")
@Mapper
public interface LogMapper {

    /**
     * @description 插入日志信息
     * @author hn
     * @date 20:50 2019/4/4
     * @Param log
     * @return int
     */
    int insert(Log log);

    /**
     * @description 根据任务Id查询
     * @author hn
     * @date 20:50 2019/4/4
     * @Param taskId
     * @return List<Log>
     */
    List<Log> selectAllLogByTaskId(String taskId);

    /**
     * @description 根据任务Id查询日志信息
     * @author hn
     * @date 20:54 2019/4/4
     * @Param logId
     * @return String
     */
    // Log findByLogId(String logId);

    /**
     * @description 根据任务删除日志信息
     * @author hn
     * @date 20:16 2019/4/4
     * @Param  taskId
     * @return int
     */
    int deleteByTaskId(String taskId);
}

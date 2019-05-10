package cn.com.voc.ControlCenter.TimerTask.service;
import cn.com.voc.ControlCenter.TimerTask.entity.Log;
import java.util.List;

/**
 * @description: 日志信息服务
 * @author: hn
 * @date: 2019/4/4
 */
public interface LogService {

    /**
     * @description 插入日志信息
     * @author hn
     * @date 20:37 2019/4/4
     * @Param log
     * @return boolean
     */
    boolean insertLog(Log log);

    /**
     * @description 根据任务id查询所有日志信息
     * @author hn
     * @date 20:39 2019/4/4
     * @Param taskId
     * @return List<Log>
     */
    List<Log> queryAllLog(String taskId);

    /**
     * @description 删除日志信息
     * @author hn
     * @date 20:18 2019/4/4
     * @Param taskId
     * @return boolean
     */
    boolean deleteLog(String taskId);

}

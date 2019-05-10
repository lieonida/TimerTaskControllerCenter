package cn.com.voc.ControlCenter.TimerTask.service.impl;

import cn.com.voc.ControlCenter.TimerTask.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.voc.ControlCenter.TimerTask.service.LogService;
import cn.com.voc.ControlCenter.TimerTask.mapper.LogMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 日志信息服务实现类
 * @author: hn
 * @date: 2019/4/4
 */
@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logMapper;

    @Override
    public boolean insertLog(Log log) {
        logMapper.insert(log);
        return false;
    }

    @Override
    public List<Log> queryAllLog(String taskId) {
        List<Log> logs = new ArrayList<Log>();
        logs = logMapper.selectAllLogByTaskId(taskId);
        return logs;
    }

    @Override
    public boolean deleteLog(String taskId) {
        logMapper.deleteByTaskId(taskId);
        return false;
    }
}

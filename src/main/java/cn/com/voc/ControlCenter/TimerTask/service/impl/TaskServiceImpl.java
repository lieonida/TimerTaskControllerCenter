package cn.com.voc.ControlCenter.TimerTask.service.impl;

import cn.com.voc.ControlCenter.TimerTask.entity.Task;
import cn.com.voc.ControlCenter.TimerTask.mapper.LogMapper;
import cn.com.voc.ControlCenter.TimerTask.service.TaskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.voc.ControlCenter.TimerTask.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 任务服务接口实现类
 * @author: hn
 * @date: 2019/4/4
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private LogMapper logMapper;

    @Override
    public boolean insertTask(Task task) {
        taskMapper.insert(task);
        return false;
    }

    @Override
    public Map<String,Object> selectAllTask(Integer pageSize, Integer pageNum,Map<String,String> map) {
        Page page = PageHelper.startPage(pageNum,pageSize,true);
        List<Task> tasks = new ArrayList<Task>();
        tasks = taskMapper.selectAllTasks(map);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("total", page.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", tasks);
        return data;
    }

    @Override
    public boolean deleteTask(String taskId) {
        logMapper.deleteByTaskId(taskId);
        taskMapper.deleteByTaskId(taskId);
        return false;
    }

    @Override
    public boolean upeateTask(Task task) {
        taskMapper.updateByTaskId(task);
        return false;
    }

    @Override
    public Task selectTask(String taskId) {
        Task task = taskMapper.findByTaskId(taskId);
        return task;
    }

    @Override
    public Task selectTaskByPath(String schedulePath) {
        Task task = taskMapper.findBySchedulePath(schedulePath);
        return task;
    }
}

package cn.com.voc.ControlCenter.TimerTask.service.impl;

import cn.com.voc.ControlCenter.TimerTask.service.ProjectService;
import cn.com.voc.ControlCenter.TimerTask.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.voc.ControlCenter.TimerTask.entity.Project;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

/**
 * @description: 项目服务接口实现类
 * @author: hn
 * @date: 2019/4/4
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public boolean insertProject(Project project){
        int flag = projectMapper.insert(project);
        return true;
    }

    @Override
    public List<Project> selectProject() {
        List<Project> list = new ArrayList<Project>();
        list =projectMapper.select();
        return list;
    }

}

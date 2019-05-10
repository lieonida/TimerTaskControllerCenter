package cn.com.voc.ControlCenter.TimerTask.controller;

import cn.com.voc.ControlCenter.TimerTask.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.com.voc.ControlCenter.TimerTask.service.ProjectService;
import cn.com.voc.ControlCenter.TimerTask.entity.Project;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private static final Logger log = Logger.getLogger("projectController.class");

    @Autowired
    private ProjectService projectService;

    @ApiOperation(value="创建项目",notes = "创建项目信息")
    @ApiImplicitParam(name = "projectName",value = "项目名称",required = true,dataType = "String")
    @PostMapping
    @ResponseBody
    public String insert(@RequestParam(value = "projectName") String projectName){
        Project project = new Project();
        project.setProjectId(StringUtil.getUUID());
        project.setProjectName(projectName);
        projectService.insertProject(project);
        return "";
    }

    @ApiOperation(value="查询项目信息列表",notes = "查询所有项目信息")
    @GetMapping
    @ResponseBody
    public List<Project> select(){
        List<Project> projects = projectService.selectProject();
        return projects;
    }

}

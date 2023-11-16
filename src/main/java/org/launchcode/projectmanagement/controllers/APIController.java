package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.ProjectManagementApplication;
import org.launchcode.projectmanagement.data.ProjectRepository;
import org.launchcode.projectmanagement.models.Epic;
import org.launchcode.projectmanagement.models.Project;
import org.launchcode.projectmanagement.models.apidto.ProjectDTO;
import org.launchcode.projectmanagement.models.apidto.SingleProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api")
public class APIController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("projects")
    @ResponseBody
    private List<ProjectDTO> loadProjects(){

        List<ProjectDTO> projects = new ArrayList<>();

        for (Project item : projectRepository.findAll()){
            ProjectDTO projectDTO = new ProjectDTO(item.getId(),item.getName(),item.getDescription(),item.getCreatedAt().toString(),item.getStatus().getDisplayName(),item.getPriority().getDisplayName(),item.getCreatedBy().getFullName(),item.getCreatedBy().getId());
            projects.add(projectDTO);
        }
        return projects;
    }

    @GetMapping("projects/{id}")
    @ResponseBody
    private SingleProjectDTO loadSingleProject(@PathVariable int id){
        Optional<Project> result = projectRepository.findById(id);
        Project item = result.get();
        SingleProjectDTO singleProjectDTO = new SingleProjectDTO(item.getId(),item.getName(),item.getDescription(),item.getCreatedAt().toString(),item.getStatus().getDisplayName(),item.getPriority().getDisplayName(),item.getCreatedBy().getFullName(),item.getCreatedBy().getId());
        for (Epic epic : item.getEpicList()){
            singleProjectDTO.addEpics(epic.getId());
        }

        return singleProjectDTO;
    }

}

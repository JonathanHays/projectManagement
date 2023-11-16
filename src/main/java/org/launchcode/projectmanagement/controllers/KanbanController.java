package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.data.EpicRepository;
import org.launchcode.projectmanagement.data.FeatureRepository;
import org.launchcode.projectmanagement.data.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.launchcode.projectmanagement.models.*;

import java.util.Optional;


@Controller
@RequestMapping("kanban")
public class KanbanController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EpicRepository epicRepository;
    @Autowired
    private FeatureRepository featureRepository;

    @GetMapping("")
    private String loadKanbanBoard(Model model) {
        model.addAttribute("title", "Kanban Board");
        Sort sort = Sort.by("name").ascending();
        model.addAttribute("projects", projectRepository.findAll(sort));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());
        model.addAttribute(new Task());

        return "kanban/view";
    }

    @PostMapping("status")
    private String loadFilteredProjectsByStatus(Model model, @RequestParam String status) {
        if (status.equals("all")) {
            model.addAttribute("title", "Kanban Board");
            Sort sort = Sort.by("name").ascending();
            model.addAttribute("projects", projectRepository.findAll(sort));
            model.addAttribute("statuses", Status.values());
            model.addAttribute(new Task());
            model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

            return "kanban/view";
        }
        Status status1 = Status.valueOf(status);
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        model.addAttribute("projects", projectRepository.findByStatusOrderByName(status1));
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/view";
    }

    @GetMapping("{id}")
    private String loadKanbanBoard(Model model, @PathVariable int id) {
        Optional<Project> restult = projectRepository.findById(id);
        Project project1 = restult.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        Sort sort = Sort.by("name").ascending();
        model.addAttribute("project", project1);
        model.addAttribute("epics",project1.getEpicList());
        model.addAttribute("url","/kanban/"+project1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/project";
    }


    @PostMapping("project")
    private String loadFilteredByProject(Model model, @RequestParam int project) {
        Optional<Project> result = projectRepository.findById(project);
        Project project1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        Sort sort = Sort.by("name").ascending();
        model.addAttribute("project", project1);
        model.addAttribute("epics",project1.getEpicList());
        model.addAttribute("url","/kanban/"+project1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/project";
    }

    @PostMapping("statusEpic")
    private String loadFilteredEpicsByStatus(Model model,@RequestParam int projectId,@RequestParam String status){
        if(status.equals("all")){
            Optional<Project> result = projectRepository.findById(projectId);
            Project project1 = result.get();
            model.addAttribute("title", "Kanban Board");
            model.addAttribute("statuses", Status.values());
            Sort sort = Sort.by("name").ascending();
            model.addAttribute("project", project1);
            model.addAttribute("epics",project1.getEpicList());
            model.addAttribute("url","/kanban/"+project1.getId());
            model.addAttribute(new Task());
            model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

            return "kanban/project";

        }
        Status status1 = Status.valueOf(status);
        Optional<Project> result = projectRepository.findById(projectId);
        Project project1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        Sort sort = Sort.by("name").ascending();
        model.addAttribute("project", project1);
        model.addAttribute("epics",project1.getEpicByStatus(status1));
        model.addAttribute("url","/kanban/"+project1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/project";
    }

    @PostMapping("epic")
    private String loadFilteredByEpic(Model model,@RequestParam int epic){
        Optional<Epic> result = epicRepository.findById(epic);
        Epic epic1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        model.addAttribute("epic", epic1);
        model.addAttribute("features",epic1.getFeatureList());
        model.addAttribute("url","/kanban/epic/"+epic1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/epic";
    }

    @GetMapping("epic/{id}")
    private String loadFilteredByEpicGet(Model model,@PathVariable int id){
        Optional<Epic> result = epicRepository.findById(id);
        Epic epic1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        model.addAttribute("epic", epic1);
        model.addAttribute("features",epic1.getFeatureList());
        model.addAttribute("url","/kanban/epic/"+epic1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());


        return "kanban/epic";
    }

    @PostMapping("statusFeature")
    private String loadFilteredFeaturesByStatus(Model model,@RequestParam int epic,@RequestParam String status){
        if(status.equals("all")){
            Optional<Epic> result = epicRepository.findById(epic);
            Epic epic1 = result.get();
            model.addAttribute("title", "Kanban Board");
            model.addAttribute("statuses", Status.values());
            model.addAttribute("epic", epic1);
            model.addAttribute("features",epic1.getFeatureList());
            model.addAttribute("url","/kanban/epic/"+epic1.getId());
            model.addAttribute(new Task());
            model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

            return "kanban/epic";
        }
        Status status1 = Status.valueOf(status);
        Optional<Epic> result = epicRepository.findById(epic);
        Epic epic1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        model.addAttribute("epic", epic1);
        model.addAttribute("features",epic1.getFeaturesByStatus(status1));
        model.addAttribute("url","/kanban/epic/"+epic1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/epic";
    }

    @PostMapping("feature")
    private String loadFeature(Model model,@RequestParam int feature){
        Optional<Feature> result = featureRepository.findById(feature);
        Feature feature1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        model.addAttribute("feature",feature1);
        model.addAttribute("tasks",feature1.getTaskList());
        model.addAttribute("url","/kanban/feature/"+feature1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/feature";
    }

    @GetMapping("feature/{feature}")
    private String loadFeatureGet(Model model,@PathVariable int feature){
        Optional<Feature> result = featureRepository.findById(feature);
        Feature feature1 = result.get();
        model.addAttribute("title", "Kanban Board");
        model.addAttribute("statuses", Status.values());
        model.addAttribute("feature",feature1);
        model.addAttribute("tasks",feature1.getTaskList());
        model.addAttribute("url","/kanban/feature/"+feature1.getId());
        model.addAttribute(new Task());
        model.addAttribute("size",Size.values());
        model.addAttribute("priority",Priority.values());

        return "kanban/feature";
    }
}

package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.data.ProjectRepository;
import org.launchcode.projectmanagement.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("projects")
public class
ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("")
    private String loadProjectView(Model model){
        model.addAttribute("title","View projects");
        Sort sort = Sort.by("name").ascending();
        model.addAttribute("allProjects",projectRepository.findAll(sort));

        return "projects/view";
    }

    @GetMapping("create")
    private String loadCreateProjectForm(Model model){
        model.addAttribute("title","Create New project");
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute(new Project());

        return "projects/create";
    }

    @PostMapping("create")
    private String processProjectCreateForm(Model model, @ModelAttribute @Valid Project project, Errors errors, HttpServletRequest request){
        if(errors.hasErrors()){
            model.addAttribute("title","Create New project");
            model.addAttribute("priority", Priority.values());
            model.addAttribute("size", Size.values());
            return "projects/create";
        }
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        project.setCreatedBy(user);
        project.setCreatedAt(LocalDateTime.now());
        project.setStatus(Status.BACKLOG);
        projectRepository.save(project);

        return "redirect:/projects";
    }

    @PostMapping("results")
    private String loadSearchResults(@RequestParam String searchTerm, Model model){
        model.addAttribute("title","View projects");
        model.addAttribute("allProjects",projectRepository.findByNameContainsIgnoreCaseOrderByName(searchTerm));

        return "projects/view";
    }

    @GetMapping("/details/{id}")
    private String loadProjectDetails(@PathVariable int id, Model model){
        Optional<Project> results = projectRepository.findById(id);
        Project project = results.get();
        model.addAttribute("title","Project: "+project.getName());
        model.addAttribute("project",project);
        model.addAttribute("statuses",Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute("epics",project.getEpicList());
        model.addAttribute(new Notes());

        return "projects/details";
    }

    @PostMapping("/update")
    private String processProjectUpdateForm(@ModelAttribute Project project){
        projectRepository.save(project);

        return "redirect:/projects/details/"+project.getId();
    }

    @PostMapping("details/results")
    private String processEpicSearch(Model model,@RequestParam int projectId,@RequestParam String searchTerm, @RequestParam String status){
        Optional<Project> result = projectRepository.findById(projectId);
        Project project = result.get();

        model.addAttribute("title","project: "+project.getName());
        model.addAttribute("project",project);
        model.addAttribute("statuses",Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute("epics",project.getFilteredEpics(searchTerm,status));

        return "projects/details";
    }

    @GetMapping("backlog/{id}")
    private String loadBackLogTask(Model model,@PathVariable int id){
        Optional<Project> result = projectRepository.findById(id);
        Project project = result.get();
        model.addAttribute("title","Backlog Task");
        model.addAttribute("tasks",project.getBackLogTask());
        model.addAttribute("project",project);

        return "tasks/backlog";
    }
}

package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.data.EpicRepository;
import org.launchcode.projectmanagement.data.ProjectRepository;
import org.launchcode.projectmanagement.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("epics")
public class EpicController {

    @Autowired
    private EpicRepository epicRepository;
    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("")
    private String loadEpicView(Model model){
        model.addAttribute("title","View Epics");
        model.addAttribute("allEpics",epicRepository.findAll());

        return "epics/view";
    }

    @GetMapping("create/{id}")
    private String loadCreateEpicForm(Model model,@PathVariable int id){

        Optional<Project> result = projectRepository.findById(id);
        Project project = result.get();
        model.addAttribute("project",project);
        model.addAttribute("title","Create New Epic");
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute(new Epic());

        return "epics/create";
    }

    @PostMapping("create")
    private String processEpicCreateForm(Model model, @ModelAttribute @Valid Epic epic, Errors errors, HttpServletRequest request,@RequestParam int projectId){
        Optional<Project> result = projectRepository.findById(projectId);
        Project project = result.get();
        model.addAttribute("project",project);

        if(errors.hasErrors()){
            model.addAttribute("title","Create New Epic");
            model.addAttribute("priority", Priority.values());
            model.addAttribute("size", Size.values());
            return "epics/create";
        }
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        epic.setProject(project);
        epic.setCreatedBy(user);
        epic.setCreatedAt(LocalDateTime.now());
        epic.setStatus(Status.BACKLOG);
        epicRepository.save(epic);

        return "redirect:/projects/details/"+epic.getProject().getId();
    }



    @GetMapping("/details/{id}")
    private String loadEpicDetails(@PathVariable int id,Model model){
        Optional<Epic> results = epicRepository.findById(id);
        Epic epic = results.get();
        model.addAttribute("title","Epic: "+epic.getName());
        model.addAttribute("epic",epic);
        model.addAttribute("statuses",Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute("features",epic.getFeatureList());

        return "epics/details";
    }

    @PostMapping("/update")
    private String processEpicUpdateForm(@ModelAttribute Epic epic){
        epicRepository.save(epic);

        return "redirect:/epics/details/"+epic.getId();
    }

    @PostMapping("details/results")
    private String processFeatureSearch(Model model,@RequestParam int epicId,@RequestParam String searchTerm, @RequestParam String status){

        Optional<Epic> results = epicRepository.findById(epicId);
        Epic epic = results.get();
        model.addAttribute("title","Epic: "+epic.getName());
        model.addAttribute("epic",epic);
        model.addAttribute("statuses",Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute("features",epic.getFilteredFeatures(searchTerm,status));

        return "epics/details";
    }



    }

package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.data.FeatureRepository;
import org.launchcode.projectmanagement.data.TaskRepository;
import org.launchcode.projectmanagement.models.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FeatureRepository featureRepository;


    @GetMapping("create/{id}")
    private String loadTaskCreateForm(Model model, @PathVariable int id) {
        Optional<Feature> result = featureRepository.findById(id);
        Feature feature = result.get();
        model.addAttribute("title", "Create New task");
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute(new Task());
        model.addAttribute("feature", feature);

        return "tasks/create";
    }


    @PostMapping("editKanban")
    private String processEditTaskFormKanban(Model model, @ModelAttribute @Valid Task task, Errors errors,
                                             @RequestParam int id, @RequestParam String editUrlId){

        Optional<Task> result = taskRepository.findById(id);
        Task originalTask = result.get();

        task.setFeature(originalTask.getFeature());
        task.setCreatedAt(originalTask.getCreatedAt());
        task.setCreatedBy(originalTask.getCreatedBy());
        task.setId(id);
        taskRepository.save(task);

        if(editUrlId.contains("feature")){
            return "redirect:/kanban/feature/"+task.getFeature().getId();
        }
        if(editUrlId.contains("epic")){
            return "redirect:/kanban/epic/"+task.getFeature().getEpic().getId();
        }
        if(editUrlId.contains("project")){
            return "redirect:/kanban/"+task.getFeature().getEpic().getProject().getId();
        }

        return "redirect:/kanban/"+task.getFeature().getEpic().getProject().getId();
    }



    @PostMapping("createKanban")
    private String processCreateTaskForm(Model model, @ModelAttribute @Valid Task task, Errors errors,
                                         @RequestParam int featureId, @RequestParam String urlId, HttpServletRequest request) {
        Optional<Feature> result = featureRepository.findById(featureId);
        Feature feature = result.get();
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        task.setFeature(feature);
        task.setCreatedAt(LocalDateTime.now());
        task.setCreatedBy(user);
        taskRepository.save(task);

        if(urlId.contains("feature")){
            return "redirect:/kanban/feature/"+feature.getId();
        }
        if(urlId.contains("epic")){
            return "redirect:/kanban/epic/"+feature.getEpic().getId();
        }
        if(urlId.contains("project")){
            return "redirect:/kanban/"+feature.getEpic().getProject().getId();
        }


        return "redirect:";
    }


    @PostMapping("create")
    private String processCreateTaskForm(Model model, @ModelAttribute @Valid Task task, Errors errors,
                                         @RequestParam int featureId, HttpServletRequest request) {
        Optional<Feature> result = featureRepository.findById(featureId);
        Feature feature = result.get();
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create New task");
            model.addAttribute("priority", Priority.values());
            model.addAttribute("size", Size.values());
            model.addAttribute("feature", feature);

            return "tasks/create";
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        task.setFeature(feature);
        task.setCreatedAt(LocalDateTime.now());
        task.setCreatedBy(user);
        task.setStatus(Status.BACKLOG);
        taskRepository.save(task);

        return "redirect:/feature/details/" + featureId;
    }

    @GetMapping("/details/{id}")
    private String loadTaskDetails(@PathVariable int id, Model model) {
        Optional<Task> results = taskRepository.findById(id);
        Task task = results.get();
        model.addAttribute("title", "Task: " + task.getName());
        model.addAttribute("task", task);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());

        return "tasks/details";
    }

    @PostMapping("/update")
    private String processTaskUpdateForm(@ModelAttribute Task task) {

        taskRepository.save(task);

        return "redirect:/tasks/details/" + task.getId();
    }


}

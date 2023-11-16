package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.data.EpicRepository;
import org.launchcode.projectmanagement.data.FeatureRepository;
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
@RequestMapping("feature")
public class FeatureController {

    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private EpicRepository epicRepository;

    @GetMapping("create/{id}")
    private String loadFeatureCreateForm(Model model, @PathVariable int id){
        Optional<Epic> result = epicRepository.findById(id);
        Epic epic = result.get();
        model.addAttribute("title","Create New Feature");
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute(new Feature());
        model.addAttribute("epic",epic);

        return "features/create";
    }

    @PostMapping("create")
    private String processCreateFeatureForm(Model model, @ModelAttribute @Valid Feature feature, Errors errors,
                                            @RequestParam int epicId, HttpServletRequest request){
        Optional<Epic> result = epicRepository.findById(epicId);
        Epic epic = result.get();
        if(errors.hasErrors()){
            model.addAttribute("title","Create New Feature");
            model.addAttribute("priority", Priority.values());
            model.addAttribute("size", Size.values());
            model.addAttribute("epic",epic);

            return "features/create";
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        feature.setEpic(epic);
        feature.setCreatedAt(LocalDateTime.now());
        feature.setCreatedBy(user);
        feature.setStatus(Status.BACKLOG);
        featureRepository.save(feature);

        return "redirect:/epics/details/"+epicId;
    }

    @GetMapping("/details/{id}")
    private String loadFeatureDetails(@PathVariable int id,Model model){
        Optional<Feature> results = featureRepository.findById(id);
        Feature feature = results.get();
        model.addAttribute("title","Feature: "+feature.getName());
        model.addAttribute("feature",feature);
        model.addAttribute("statuses",Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute("tasks",feature.getTaskList());

        return "features/details";
    }

    @PostMapping("/update")
    private String processEpicUpdateForm(@ModelAttribute Feature feature){
        featureRepository.save(feature);

        return "redirect:/feature/details/"+feature.getId();
    }

    @PostMapping("details/results")
    private String processTaskSearch(Model model,@RequestParam int featureId,@RequestParam String searchTerm, @RequestParam String status){

        Optional<Feature> results = featureRepository.findById(featureId);
        Feature feature = results.get();
        model.addAttribute("title","Feature: "+feature.getName());
        model.addAttribute("feature",feature);
        model.addAttribute("statuses",Status.values());
        model.addAttribute("priority", Priority.values());
        model.addAttribute("size", Size.values());
        model.addAttribute("tasks",feature.getFilteredTask(searchTerm,status));

        return "features/details";
    }
}

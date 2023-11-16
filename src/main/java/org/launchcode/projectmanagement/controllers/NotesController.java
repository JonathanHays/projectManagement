package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.data.*;
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
@RequestMapping("notes")
public class NotesController {

    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private EpicRepository epicRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private NotesRepository notesRepository;

    @PostMapping("project/create")
    private String createNewNoteProject(@RequestParam String notes, @RequestParam int project, HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Optional<Project> result = projectRepository.findById(project);
        Project project1 = result.get();
        Notes newNote = new Notes(notes,user,project1, LocalDateTime.now());

        notesRepository.save(newNote);

        return "redirect:/projects/details/"+project;

    }

    @PostMapping("epic/create")
    private String createNewNoteEpic(@RequestParam String notes, @RequestParam int epic, HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Optional<Epic> result = epicRepository.findById(epic);
        Epic epic1 = result.get();
        Notes newNote = new Notes(notes,user,epic1, LocalDateTime.now());

        notesRepository.save(newNote);

        return "redirect:/epics/details/"+epic;

    }

    @PostMapping("feature/create")
    private String createNewNoteFeature(@RequestParam String notes, @RequestParam int feature, HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Optional<Feature> result = featureRepository.findById(feature);
        Feature feature1 = result.get();
        Notes newNote = new Notes(notes,user,feature1, LocalDateTime.now());

        notesRepository.save(newNote);

        return "redirect:/feature/details/"+feature;

    }

    @PostMapping("task/create")
    private String createNewNoteTask(@RequestParam String notes, @RequestParam int task, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Optional<Task> result = taskRepository.findById(task);
        Task task1 = result.get();
        Notes newNote = new Notes(notes, user, task1, LocalDateTime.now());

        notesRepository.save(newNote);

        return "redirect:/tasks/details/" + task;
    }


 @PostMapping("task/createKanban")
    private String createNewNoteTaskKanban(@RequestParam String notes,@RequestParam String url, @RequestParam int task, HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Optional<Task> result = taskRepository.findById(task);
        Task task1 = result.get();
        Notes newNote = new Notes(notes,user,task1, LocalDateTime.now());

        notesRepository.save(newNote);


        return "redirect:"+url;

    }
}

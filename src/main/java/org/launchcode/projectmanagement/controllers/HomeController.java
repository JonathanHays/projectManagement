package org.launchcode.projectmanagement.controllers;


import org.launchcode.projectmanagement.data.FileRepository;
import org.launchcode.projectmanagement.data.ProjectRepository;
import org.launchcode.projectmanagement.data.TaskRepository;
import org.launchcode.projectmanagement.models.Status;
import org.launchcode.projectmanagement.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("")
    private String loadHomePageActiveProjects(Model model) {
        model.addAttribute("title", "Project Management");
        Status status = Status.INPROGRES;
        model.addAttribute("projects", projectRepository.findByStatusOrderByName(status));

        return "index";
    }

    @GetMapping("modal")
    private String loadModal(Model model) {
        model.addAttribute("title", "Modal Testing");
        model.addAttribute("projects", projectRepository.findAll());

        return "modal/modal";
    }

    @GetMapping("test")
    private String loadTest(Model model){
        Optional<Task> task = taskRepository.findById(7);
        model.addAttribute("test",task.get());
        return "test";
    }


}

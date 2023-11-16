package org.launchcode.projectmanagement.controllers;

import org.launchcode.projectmanagement.FileUploadUtil;
import org.launchcode.projectmanagement.data.FileRepository;
import org.launchcode.projectmanagement.data.ProjectRepository;
import org.launchcode.projectmanagement.data.TaskRepository;
import org.launchcode.projectmanagement.models.File;
import org.launchcode.projectmanagement.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("createTask")
    private String fileCreateKanban(@RequestParam String urlId,Model model,@RequestParam int taskId, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        Optional<Task> result = taskRepository.findById(taskId);
        Task task = result.get();

        String fileName = task.getId()+"_"+task.getFileListSize()+"."+StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
//        String fileName =StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File file = new File();
        file.setFile(fileName);
        file.setTask(task);
        fileRepository.save(file);
        String uploadDir = "user-photos/tasks/"+task.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        
        if(urlId.contains("/kanban/feature")){
            return "redirect:/kanban/feature/"+task.getFeature().getId();
        } else  if(urlId.contains("/kanban/epic")){
            return "redirect:/kanban/epic/"+task.getFeature().getEpic().getId();
        } else  if(urlId.contains("/kanban")){
            return "redirect:/kanban/"+task.getFeature().getEpic().getProject().getId();
        }
        
        
        return "redirect:"+urlId;
    }

    @PostMapping("create")
    private String fileCreateTask(Model model,@RequestParam int taskId, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        Optional<Task> result = taskRepository.findById(taskId);
        Task task = result.get();

        String fileName = task.getId()+"_"+task.getFileListSize()+"."+StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
//        String fileName =StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File file = new File();
        file.setFile(fileName);
        file.setTask(task);
        fileRepository.save(file);
        String uploadDir = "user-photos/tasks/"+task.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
   
        
        return "redirect:/tasks/details/"+task.getId();
    }
}

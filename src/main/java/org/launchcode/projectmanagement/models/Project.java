package org.launchcode.projectmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name Cannot Be Blank")
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Status status;
    private Priority priority;
    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "project")
    private final List<Epic> epicList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private final List<Notes> notesList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Epic> getEpicList() {
        return epicList;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public Integer getEpicCount(){
        return  epicList.size();
    }

    public Integer completedEpics(){

        Integer sum = 0;
        Status status = Status.COMPLETE;

        for(Epic item : epicList){
            if (item.getStatus().equals(status)){
                sum+=1;
            }
        }
        return sum;
    }

    public Integer openEpics(){
        return getEpicCount()-completedEpics();
    }

    public List<Epic> getFilteredEpics(String searchTerm,String status){

        List<Epic> epics = new ArrayList<>();



        if(status.equals("all")){
            for(Epic item : epicList){
                if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                    epics.add(item);
                }
            }
        } else {
            Status status1 = Status.valueOf(status);
            for(Epic item : epicList){
                if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())&&item.getStatus().equals(status1)){
                    epics.add(item);
                }
            }
        }
        return epics;
    }

    public List<Task> getBackLogTask(){
        List<Task> tasks = new ArrayList<>();

        for(Epic epic : epicList){
            for(Feature feature : epic.getFeatureList()){
                for (Task task : feature.getTaskList()){
                    if(task.getStatus()==Status.BACKLOG){
                        tasks.add(task);
                    }
                }
            }
        }

        return tasks;
    }

    public List<Epic> getEpicByStatus(Status status){
        List<Epic> epics = new ArrayList<>();

        for(Epic epic :epicList){
            if(epic.getStatus()==status){
                epics.add(epic);
            }
        }
        return epics;
    }
}

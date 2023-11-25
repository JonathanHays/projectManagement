package org.launchcode.projectmanagement.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name Cannot Be Blank")
    private String Name;
    private Status status;
    private Size size;
    private Priority priority;
    private String description;
    private LocalDateTime createdAt;
    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Feature feature;

    @OneToMany(mappedBy = "task")
    private final List<Notes> notesList = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private final List<File> fileList =new ArrayList<>();

    public Task() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public List<Notes> getCommentList() {
        return notesList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public int getFileListSize(){
        return fileList.size();
    }

    public List<String> getFileURLS(){

        List<String> urls = new ArrayList<>();

        for(File item : fileList){
            urls.add("/user-photos/tasks/"+this.getId()+"/" + item.getFile());
            }

        return urls;
    }

    public List<File> getFileList() {
        return fileList;
    }
}

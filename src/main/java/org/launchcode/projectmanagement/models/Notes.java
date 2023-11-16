package org.launchcode.projectmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Cannot be blank")
    private String comment;
    @ManyToOne
    private User createdBy;
    private LocalDateTime createdAt;

    @ManyToOne
    private Task task;
    @ManyToOne
    private Epic epic;
    @ManyToOne
    private Feature feature;
    @ManyToOne
    private Project project;

    public Notes() {
    }

    public Notes(String comment,User createdBy,Project project,LocalDateTime createdAt){
        this.comment = comment;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.project = project;
    }
    public Notes(String comment,User createdBy,Epic epic,LocalDateTime createdAt){
        this.comment = comment;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.epic = epic;
    }
    public Notes(String comment,User createdBy,Feature feature,LocalDateTime createdAt){
        this.comment = comment;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.feature = feature;
    }
    public Notes(String comment,User createdBy,Task task,LocalDateTime createdAt){
        this.comment = comment;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.task = task;
    }



    public Project getProject() {
        return project;
    }

    public String getDate(){
        return createdAt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}

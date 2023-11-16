package org.launchcode.projectmanagement.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Name;
    private Status status;
    private Size size;
    private Priority priority;
    private String description;
    private LocalDateTime createdAt;
    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Epic epic;

    @OneToMany(mappedBy = "feature")
    private final List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "feature")
    private final List<Notes> notesList = new ArrayList<>();


    public Feature() {
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

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Integer taskCount(){
        return taskList.size();
    }

    public Integer completedTask(){

        Integer sum = 0;
        Status status = Status.COMPLETE;

        for(Task item : taskList){
            if (item.getStatus().equals(status)){
                sum+=1;
            }
        }
        return sum;
    }

    public Integer openTask(){
        return taskCount()-completedTask();
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

    public List<Task> getFilteredTask(String searchTerm,String status){

        List<Task> tasks = new ArrayList<>();

        if(status.equals("all")){
            for(Task item : taskList){
                if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                    tasks.add(item);
                }
            }
        } else {
            Status status1 = Status.valueOf(status);
            for(Task item : taskList){
                if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())&&item.getStatus().equals(status1)){
                    tasks.add(item);
                }
            }
        }
        return tasks;
    }

    public List<Task> getCompleteTask(){
        List<Task> tasks = new ArrayList<>();
        Status status = Status.COMPLETE;
        for(Task item : taskList){
            if (item.getStatus().equals(status)){
                tasks.add(item);
            }
        }
        return  tasks;
    }

    public List<Task> getTestTask(){
        List<Task> tasks = new ArrayList<>();
        Status status = Status.TESTING;
        for(Task item : taskList){
            if (item.getStatus().equals(status)){
                tasks.add(item);
            }
        }
        return  tasks;
    }


    public List<Task> getReadyTask(){
        List<Task> tasks = new ArrayList<>();
        Status status = Status.READY;
        for(Task item : taskList){
            if (item.getStatus().equals(status)){
                tasks.add(item);
            }
        }
        return  tasks;
    }


    public List<Task> getInProgressTask(){
        List<Task> tasks = new ArrayList<>();
        Status status = Status.INPROGRES;
        for(Task item : taskList){
            if (item.getStatus().equals(status)){
                tasks.add(item);
            }
        }
        return  tasks;
    }
}

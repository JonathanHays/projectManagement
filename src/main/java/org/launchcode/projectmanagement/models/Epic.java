package org.launchcode.projectmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Epic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name Cannot Be Blank")
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Status status;
    private Priority priority;
    private Size size;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "epic")
    private final  List<Feature> featureList = new ArrayList<>();

    @OneToMany(mappedBy = "epic")
    private final  List<Notes> notesList = new ArrayList<>();

    public Epic() {
    }


    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer featureCount(){
        return featureList.size();
    }

    public Project getProject() {
        return project;
    }

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

    public List<Feature> getFeatureList() {
        return featureList;
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }




    public Integer completedFeatures(){

        Integer sum = 0;
        Status status = Status.COMPLETE;

        for(Feature item : featureList){
            if (item.getStatus().equals(status)){
                sum+=1;
            }
        }
        return sum;
    }

    public Integer openFeatures(){
        return featureCount()-completedFeatures();
    }

    public List<Feature> getFeaturesByStatus(Status status){
        List<Feature> features = new ArrayList<>();

        for(Feature feature : featureList){
            if(feature.getStatus()==status){
                features.add(feature);
            }
        }
        return features;
    }

    public List<Feature> getFilteredFeatures(String searchTerm,String status){

        List<Feature> features = new ArrayList<>();



        if(status.equals("all")){
            for(Feature item : featureList){
                if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                    features.add(item);
                }
            }
        } else {
            Status status1 = Status.valueOf(status);
            for(Feature item : featureList){
                if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())&&item.getStatus().equals(status1)){
                    features.add(item);
                }
            }
        }
        return features;
    }
}

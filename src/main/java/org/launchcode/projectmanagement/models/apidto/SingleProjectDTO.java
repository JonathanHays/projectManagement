package org.launchcode.projectmanagement.models.apidto;

import java.util.ArrayList;
import java.util.List;

public class SingleProjectDTO {

    private int id;
    private String name;
    private String description;
    private String createdAt;
    private String status;
    private String priority;
    private String createdBy;
    private int createdByID;
    private List<Integer> epicIds = new ArrayList<>();

    public SingleProjectDTO(int id, String name, String description, String createdAt, String status, String priority, String createdBy,int createdByID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.priority = priority;
        this.createdBy = createdBy;
        this.createdByID = createdByID;
    }



    public int getCreatedByID() {
        return createdByID;
    }

    public void setCreatedByID(int createdByID) {
        this.createdByID = createdByID;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void addEpics(Integer epic){
        this.epicIds.add(epic);
    }

    public List<Integer> getEpicIds() {
        return epicIds;
    }

    public void setEpicIds(List<Integer> epicIds) {
        this.epicIds = epicIds;
    }
}

package org.launchcode.projectmanagement.models;

import jakarta.persistence.*;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 64)
    private String file;

    @ManyToOne
    private Task task;

    public File() {
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Transient
    public String getFilePath() {
        if (file == null ) return null;
        return "/user-photos/tasks/" + task.getId() + "/" + file;
    }
}

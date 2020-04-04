package be.ucll.ip.joerymertens.taskmanager.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Task {
    @Id
    private UUID uuid = UUID.randomUUID();
    @Column(name= "duedate")
    private String dueDate;
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private List<SubTask> subTasks = new ArrayList<SubTask>();

    public Task(){};

    public Task(String title, String dueDate, String description){
        this.title=title;
        this.dueDate=dueDate;
        this.description=description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public void addSubTask(SubTask subTask){
        this.subTasks.add(subTask);
    }
}

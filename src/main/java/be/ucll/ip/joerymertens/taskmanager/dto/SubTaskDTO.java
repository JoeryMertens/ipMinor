package be.ucll.ip.joerymertens.taskmanager.dto;

import javax.validation.constraints.NotNull;

public class SubTaskDTO {
    @NotNull
    private String title, description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
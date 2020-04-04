package be.ucll.ip.joerymertens.taskmanager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class SubTask {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String title;
    private String description;

    public SubTask(){};

    public SubTask(String title, String description){
        this.title=title;
        this.description=description;
    }


    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}

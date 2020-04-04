package be.ucll.ip.joerymertens.taskmanager.repositories;


import be.ucll.ip.joerymertens.taskmanager.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {
}
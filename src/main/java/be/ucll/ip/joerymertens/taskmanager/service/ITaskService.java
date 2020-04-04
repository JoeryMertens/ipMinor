package be.ucll.ip.joerymertens.taskmanager.service;

import be.ucll.ip.joerymertens.taskmanager.domain.Task;
import be.ucll.ip.joerymertens.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.joerymertens.taskmanager.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
    List<TaskDTO> getTasks();

    TaskDTO getTask(UUID id);

    void createTask(TaskDTO task);

    void editTask(UUID id, TaskDTO TaskDTO);

    void createSubTask(UUID id, SubTaskDTO subTask);
}

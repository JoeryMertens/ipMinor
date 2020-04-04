

package be.ucll.ip.joerymertens.taskmanager.service;

import be.ucll.ip.joerymertens.taskmanager.domain.SubTask;
import be.ucll.ip.joerymertens.taskmanager.domain.Task;
import be.ucll.ip.joerymertens.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.joerymertens.taskmanager.repositories.TaskRepository;
import be.ucll.ip.joerymertens.taskmanager.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getTasks() {
        ArrayList<TaskDTO> result = new ArrayList<>();
        taskRepository.findAll().forEach(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDueDate(task.getDueDate());
            taskDTO.setUuid(task.getUuid());
            result.add(taskDTO);
        });
        return result;
    }

    @Override
    public TaskDTO getTask(UUID id) {
        return taskRepository.findById(id).map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDueDate(task.getDueDate());
            taskDTO.setUuid(task.getUuid());
            taskDTO.setSubTasks(task.getSubTasks().stream().map(y -> {
                SubTaskDTO subTaskDTO = new SubTaskDTO();
                subTaskDTO.setTitle(y.getTitle());
                subTaskDTO.setDescription(y.getDescription());
                return subTaskDTO;
            }).collect(Collectors.toList()));
            return taskDTO;
        }).orElse(null);
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDueDate());
        taskRepository.save(task);
    }

    @Override
    public void editTask(UUID id, TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDueDate());
        task.setUuid(id);
        taskRepository.save(task);
    }

    @Override
    public void createSubTask(UUID id, SubTaskDTO subTaskDTO) {
        Task task = taskRepository.findById(id).get();
        SubTask subTask = new SubTask(subTaskDTO.getTitle(), subTaskDTO.getDescription());
        task.addSubTask(subTask);
        taskRepository.save(task);
    }
}

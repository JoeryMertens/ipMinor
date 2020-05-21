package be.ucll.ip.joerymertens.taskmanager;

import be.ucll.ip.joerymertens.taskmanager.domain.Task;
import be.ucll.ip.joerymertens.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.joerymertens.taskmanager.dto.TaskDTO;
import be.ucll.ip.joerymertens.taskmanager.repositories.TaskRepository;
import be.ucll.ip.joerymertens.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TaskTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testCreateTask() {

    }

    @Test
    public void testGetTasks() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("titel");
        taskDTO.setDescription("beschrijving");
        taskDTO.setDueDate("vandaag");
        taskService.createTask(taskDTO);

        // method to be tested
        List<TaskDTO> tasks = taskService.getTasks();

        // checks
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
        assertEquals("titel", task.getTitle());
        assertEquals("beschrijving", task.getDescription());
        assertEquals("vandaag", task.getDueDate());
    }

    @Test
    public void testGetSingleTaskWithSubTasks() {
        UUID uuid = UUID.randomUUID();
        // setup
        Task task = new Task();
        task.setTitle("titel");
        task.setDescription("beschrijving");
        task.setDueDate("vandaag");
        task.setUuid(uuid);
        taskRepository.save(task);
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setDescription("wow subTask");
        subTaskDTO.setTitle("doen!");

        taskService.createSubTask(uuid, subTaskDTO);
        // method to be tested
        TaskDTO taskDTO = taskService.getTask(uuid);

        // checks
        assertNotNull(taskDTO);
        assertEquals("titel", taskDTO.getTitle());
        assertEquals("beschrijving", taskDTO.getDescription());
        assertEquals("vandaag", taskDTO.getDueDate());
        assertEquals(subTaskDTO.getDescription(), taskDTO.getSubTasks().get(0).getDescription());
        assertEquals(subTaskDTO.getTitle(), taskDTO.getSubTasks().get(0).getTitle());
    }
}

package be.ucll.ip.joerymertens.taskmanager.controller;

import be.ucll.ip.joerymertens.taskmanager.domain.SubTask;
import be.ucll.ip.joerymertens.taskmanager.domain.Task;
import be.ucll.ip.joerymertens.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.joerymertens.taskmanager.dto.TaskDTO;
import be.ucll.ip.joerymertens.taskmanager.repositories.TaskRepository;
import be.ucll.ip.joerymertens.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    private String getIndex(Model model){
        return "index.html";
    }
    @GetMapping("/tasks")
    private String  getTasks(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks.html";
    }
    @GetMapping("/tasks/new")
    private String getForm(Model model){
        model.addAttribute("edit", false);
        model.addAttribute("task", new Task());
        return "addTask.html";
    }

    @GetMapping("/tasks/{id}")
    private String  getTaskDetail(Model model, @PathVariable(value = "id") UUID id){
        model.addAttribute("task", taskService.getTask(id));
        return "taskDetail.html";
    }

    @PostMapping("/tasks/create")
    private String  createTask(Model model, @ModelAttribute @Valid TaskDTO task ){
        taskService.createTask(task);
        return "redirect:/tasks";
    }
    @GetMapping("/tasks/edit/{id}")
    private String editTask(Model model, @PathVariable(value = "id") UUID id){
        model.addAttribute("edit", true);
        model.addAttribute("id", id);
        model.addAttribute("task", taskService.getTask(id));
        return "addTask.html";
    }
    @PostMapping("/tasks/edit")
    private String  editTask(Model model,@RequestParam(value="id") UUID id,@ModelAttribute @Valid TaskDTO taskDTO ){
        taskService.editTask(id, taskDTO);
        return "redirect:/tasks/"+id;
    }
    @GetMapping("/tasks/{id}/sub/create")
    private String createSubTask(Model model, @PathVariable(value = "id") UUID id){
        model.addAttribute("taskId",id);
        model.addAttribute("edit", false);
        model.addAttribute("subTask", new SubTask());
        return"addSubTask.html";
    }
    @PostMapping("/tasks/{id}/sub/create")
    private String  createSubTask(Model model, @PathVariable(value = "id") UUID id, @ModelAttribute @Valid SubTaskDTO subTaskDTO){
        taskService.createSubTask(id,subTaskDTO);
        return "redirect:/tasks/"+id;
    }

}

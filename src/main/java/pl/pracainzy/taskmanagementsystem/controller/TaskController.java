package pl.pracainzy.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pracainzy.taskmanagementsystem.model.Task;
import pl.pracainzy.taskmanagementsystem.repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("")
    public List<Task> getAll(){
        return taskRepository.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable("id") Long id){
        return taskRepository.getById(id);
    }

    @PostMapping("")
    public String addTask(@RequestBody Task task) {
        taskRepository.addTask(List.of(task));
        return "Task added successfully";
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable("id") Long id, @RequestBody Task updatedTask){
        Task task = taskRepository.getById(id);
        if (task != null){
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setDeadline(updatedTask.getDeadline());

            return taskRepository.updateTask(task);
        } else {
            return "Task not found";
        }
   }

   @PatchMapping("/{id}")
    public String updateStatus(@PathVariable("id") Long id, @RequestBody Task updatedStatus){
        Task task = taskRepository.getById(id);
        if (task != null){
            task.setStatus(updatedStatus.getStatus());

            return taskRepository.updateStatus(task);
        } else {
            return "Task not found";
        }
   }

   @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable("id") Long id){
        return taskRepository.deleteTask(id);
   }

   @PostMapping("/{id}/assign/{userId}")
    public String assignUserToTask(@PathVariable("id") Long id, @PathVariable("userId") Long userId){
        Task task = taskRepository.getById(id);
        if (task != null) {
            task.setUser_id(userId);
            taskRepository.updateTask(task);
            return "User assigned to task successfully";
        } else {
            return "Task not found";
        }
   }

}

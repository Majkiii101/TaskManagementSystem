package pl.pracainzy.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import pl.pracainzy.taskmanagementsystem.model.Task;
import pl.pracainzy.taskmanagementsystem.model.User;
import pl.pracainzy.taskmanagementsystem.repository.TaskRepository;
import pl.pracainzy.taskmanagementsystem.repository.UserRepository;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public IndexController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("task", new Task()); // Dodanie pustego obiektu Task
        return "home";
    }


    @PostMapping("/index/addUser")
    public String addUser(@ModelAttribute User user){
        userRepository.addUser(List.of(user));
        return "redirect:/";
    }

    @PostMapping("/index/addTask")
    public String addTask(@ModelAttribute Task task) {
        System.out.println("Dodawanie zadania: " + task);
        taskRepository.addTask(List.of(task));
        return "redirect:/";
    }


}

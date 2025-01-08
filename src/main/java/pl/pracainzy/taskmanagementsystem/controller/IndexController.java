package pl.pracainzy.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pracainzy.taskmanagementsystem.model.Task;
import pl.pracainzy.taskmanagementsystem.model.User;
import pl.pracainzy.taskmanagementsystem.repository.TaskRepository;
import pl.pracainzy.taskmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        model.addAttribute("task", new Task());

        // Pobranie list użytkowników i zadań
        List<User> users = userRepository.getAll();
        List<Task> tasks = taskRepository.getAll();

        // Dodanie do modelu
        model.addAttribute("users", users);
        model.addAttribute("tasks", tasks);

        // Tworzenie mapy użytkowników
        Map<Long, String> userMap = users.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        user -> user.getFirst_name() + " " + user.getLast_name())
                );

        // Przekazanie mapy użytkowników do widoku
        model.addAttribute("userMap", userMap);

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

    @PostMapping("/index/updateStatus")
    public String updateStatus(@RequestParam("taskId") Long taskId, @RequestParam("status") String status){
        Task task = taskRepository.getById(taskId);
        if (task != null){
            task.setStatus(status);
            taskRepository.updateStatus(task);
        }
        return "redirect:/";
    }

    @PostMapping("/index/deleteTask")
    public String deleteTask(@RequestParam("taskId") Long taskId){
        taskRepository.deleteTask(taskId);
        return "redirect:/";
    }


}

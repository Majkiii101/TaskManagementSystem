package pl.pracainzy.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pracainzy.taskmanagementsystem.model.User;
import pl.pracainzy.taskmanagementsystem.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> getAll() { return userRepository.getAll(); }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) { return userRepository.getById(id); }

    @PostMapping("")
    public String addUser(@RequestBody User user){
        userRepository.addUser(List.of(user));
        return "User added successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser){
        User user = userRepository.getById(id);
        if (user != null){
            user.setFirst_name(updatedUser.getFirst_name());
            user.setLast_name(updatedUser.getLast_name());
            user.setEmail(updatedUser.getEmail());

            return userRepository.updateUser(user);
        } else{
            return "User not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        return userRepository.deleteUser(id);
    }



}



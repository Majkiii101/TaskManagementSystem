package pl.pracainzy.taskmanagementsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pracainzy.taskmanagementsystem.model.User;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll(){
        return jdbcTemplate.query("SELECT * FROM user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id=?",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public String addUser(List<User> users){
        users.forEach(user -> jdbcTemplate.update("INSERT INTO user(first_name, last_name, email) VALUES (?, ?, ?)",
                user.getFirst_name(), user.getLast_name(), user.getEmail()));
        return "Added new user";
    }

    public String updateUser(User user){
        jdbcTemplate.update("UPDATE user SET first_name=?, last_name=?, email=? WHERE id=?",
                user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getId());
        return "User updated";
    }

    public String deleteUser(Long id){
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
        return "User deleted";
    }

}

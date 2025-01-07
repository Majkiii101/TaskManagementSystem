package pl.pracainzy.taskmanagementsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pracainzy.taskmanagementsystem.model.Task;

import java.util.List;

@Repository
public class TaskRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Task> getAll(){
        return jdbcTemplate.query("SELECT * FROM task",
                BeanPropertyRowMapper.newInstance(Task.class));
    }

    public Task getById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM task WHERE id=?",
                BeanPropertyRowMapper.newInstance(Task.class), id);
    }


    public void addTask(List<Task> tasks) {
        for (Task task : tasks) {
            jdbcTemplate.update(
                    "INSERT INTO task (title, description, status, priority, deadline, user_id) VALUES (?, ?, ?, ?, ?, ?)",
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getDeadline(), // Dodaj pole deadline
                    task.getUser_id()   // user_id może być null
            );
        }
    }

    public String updateTask(Task task){
        jdbcTemplate.update("UPDATE task SET title=?, description=?, status=?, priority=?, deadline=?, user_id=? WHERE id=?",
               task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDeadline(), task.getUser_id(), task.getId());
        return "Task updated";
    }

    public String updateStatus(Task task){
        jdbcTemplate.update("UPDATE task SET status=? WHERE id=?",
                task.getStatus(), task.getId());
        return "Status updated";
    }

    public int deleteTask(Long id){
        return jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }

    public String assignUserToTask(Long taskId, Long userId){
        jdbcTemplate.update("UPDATE task SET user_id=? WHERE id=?", userId, taskId);
        return "User assigned to task";
    }
}

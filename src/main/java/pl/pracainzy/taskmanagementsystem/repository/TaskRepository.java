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
        return jdbcTemplate.query("SELECT id, title, description, status, priority, deadline FROM task",
                BeanPropertyRowMapper.newInstance(Task.class));
    }

    public Task getByID(Long id){
        return jdbcTemplate.queryForObject("SELECT id, title, description, status, priority, deadline FROM task WHERE id=?",
                BeanPropertyRowMapper.newInstance(Task.class), id);
    }

    public String add(List<Task> tasks) {
        tasks.forEach(task ->  jdbcTemplate
                .update("INSERT INTO task(title, description, status, priority, deadline) VALUES(?, ?, ?, ?, ?)",
                        task.getTitle(), task.getDeadline(), task.getStatus(), task.getPriority(), task.getDeadline()
                ));

        return "New task added";
    }

    public String updateTask(Task task){
        jdbcTemplate.update("UPDATE task SET title=?, description=?, status=?, priority=?, deadline=? WHERE id=?",
               task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDeadline(), task.getId());
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
}

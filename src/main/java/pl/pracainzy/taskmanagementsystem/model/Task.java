package pl.pracainzy.taskmanagementsystem.model;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date deadline;
    private Long user_id;

    public Task(String title, String description, String status, String priority, Date deadline, Long user_id){
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
        this.user_id = user_id;
    }

}

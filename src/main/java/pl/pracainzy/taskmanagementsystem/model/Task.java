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

    public Task(String title, String description, String status, String priority, Date deadline){
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
    }

}

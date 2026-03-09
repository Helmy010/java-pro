package model;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDateTime dueDateTime;

    public Task(int id, String title, String description,
                Priority priority, Status status,
                LocalDateTime dueDateTime) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDateTime = dueDateTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void markDone() {
        status = Status.DONE;
    }

    public void setStatus(Status status){
        this.status = status;
    }
}
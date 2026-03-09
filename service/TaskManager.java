package service;

import model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks = new ArrayList<>();
    private int idCounter = 1;

    public void addTask(String title, String description,
                        Priority priority,
                        LocalDateTime dueDateTime) {

        Task task = new Task(
                idCounter++,
                title,
                description,
                priority,
                Status.PENDING,
                dueDateTime
        );

        tasks.add(task);
    }

    public void deleteTask(int id){
        tasks.removeIf(t -> t.getId() == id);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void checkOverdue(){
        LocalDateTime now = LocalDateTime.now();
        for(Task t : tasks){
            if(t.getStatus() != Status.DONE &&
               t.getDueDateTime().isBefore(now)){
                t.setStatus(Status.OVERDUE);
            }
        }
    }
}
package kg.megalab.taskflow.service;

import kg.megalab.taskflow.model.Task;
import kg.megalab.taskflow.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    public List<Task> findAll() {
        return List.of();
    }

    public Task create(Task task) {
        task.setId(1L);
        task.setStatus(TaskStatus.NEW);
        task.setCreatedAt(LocalDateTime.now());
        return task;
    }
}

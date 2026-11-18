package kg.megalab.taskflow.service;

import kg.megalab.taskflow.model.Task;
import kg.megalab.taskflow.model.TaskStatus;
import org.springframework.stereotype.Service;
import kg.megalab.taskflow.exception.TaskNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1L;

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public Task create(Task task) {
        task.setId(nextId++);
        task.setStatus(TaskStatus.NEW);
        task.setCreatedAt(LocalDateTime.now());
        tasks.add(task);

        return task;
    }

    public Task findById(Long id) {
        return findAll().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task update(Long id, Task updatedTask) {
        Task task = findById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());

        return task;
    }

    public void delete(Long id) {
        Task task = findById(id);
        tasks.remove(task);
    }

    public List<Task> findAll(TaskStatus status, String search) {
        return tasks.stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> search == null || (task.getTitle() != null && task.getTitle().toLowerCase()
                        .contains(search.toLowerCase()))).sorted(Comparator.comparing(
                        Task::getCreatedAt).reversed()).toList();
    }
}

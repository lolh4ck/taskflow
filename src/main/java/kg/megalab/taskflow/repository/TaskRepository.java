package kg.megalab.taskflow.repository;

import kg.megalab.taskflow.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    boolean existsById(Long id);
    void deleteById(Long id);
}

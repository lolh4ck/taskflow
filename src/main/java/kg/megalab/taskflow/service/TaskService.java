package kg.megalab.taskflow.service;

import kg.megalab.taskflow.dto.TaskCreateRequest;
import kg.megalab.taskflow.dto.TaskResponse;
import kg.megalab.taskflow.dto.TaskUpdateRequest;
import kg.megalab.taskflow.mapper.TaskMapper;
import kg.megalab.taskflow.model.Task;
import kg.megalab.taskflow.model.TaskStatus;
import kg.megalab.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;
import kg.megalab.taskflow.exception.TaskNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Comparator;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> findAll() {
        return findAll(null, null);
    }

    public TaskResponse create(TaskCreateRequest request) {
        Task task = taskMapper.toEntity(request);

        task.setStatus(TaskStatus.NEW);
        task.setCreatedAt(LocalDateTime.now());

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public TaskResponse findById(Long id) {
        Task task = getTaskOrThrow(id);
        return taskMapper.toResponse(task);
    }

    private Task getTaskOrThrow(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskResponse update(Long id, TaskUpdateRequest request) {
        Task task = getTaskOrThrow(id);
        taskMapper.updateEntity(task, request);

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    public List<TaskResponse> findAll(TaskStatus status, String search) {
        return taskRepository.findAll().stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> search == null || search.isBlank()
                        || (task.getTitle() != null && task.getTitle().toLowerCase().contains(search.toLowerCase())))
                .sorted(Comparator.comparing(Task::getCreatedAt).reversed())
                .map(taskMapper::toResponse).toList();
    }
}

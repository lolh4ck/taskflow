package kg.megalab.taskflow.controller;

import kg.megalab.taskflow.dto.TaskCreateRequest;
import kg.megalab.taskflow.dto.TaskResponse;
import kg.megalab.taskflow.dto.TaskUpdateRequest;
import kg.megalab.taskflow.model.TaskStatus;
import kg.megalab.taskflow.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> findAll(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String search) {

        return taskService.findAll(status, search);
    }

    @GetMapping("{id}")
    public TaskResponse findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskCreateRequest request){
        TaskResponse created = taskService.create(request);
        URI location = URI.create("/api/v1/tasks/" + created.id());

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskUpdateRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

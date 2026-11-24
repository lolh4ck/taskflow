package kg.megalab.taskflow.repository;

import kg.megalab.taskflow.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<Long, Task> storage = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(nextId.getAndIncrement());
        }

        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}

package kg.megalab.taskflow.dto;

import kg.megalab.taskflow.model.TaskStatus;

public record TaskUpdateRequest(String title, String description, TaskStatus status) {
}

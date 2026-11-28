package kg.megalab.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.megalab.taskflow.model.TaskStatus;
import kg.megalab.taskflow.validation.AssigneeRequiredForDone;

import java.time.LocalDate;

@AssigneeRequiredForDone
public record TaskUpdateRequest(
        @NotBlank(message = "Название не может быть пустым!")
        @Size(min = 3, max = 100, message = "Название должно содержать от {min} до {max} символов!")
        String title,

        @Size(max = 1000, message = "Описание не длиннее {max} символов!")
        String description,

        @NotNull(message = "Статус обязателен!")
        TaskStatus status,

        LocalDate deadline,

        @Size(max = 100, message = "Имя исполнителя не длиннее {max} символов!")
        String assignee
) {
}

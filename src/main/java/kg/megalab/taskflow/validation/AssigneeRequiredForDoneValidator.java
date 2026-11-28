package kg.megalab.taskflow.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.megalab.taskflow.dto.TaskUpdateRequest;
import kg.megalab.taskflow.model.TaskStatus;

public class AssigneeRequiredForDoneValidator
        implements ConstraintValidator<AssigneeRequiredForDone, TaskUpdateRequest> {

    @Override
    public boolean isValid(TaskUpdateRequest request, ConstraintValidatorContext context) {

        if (request == null || request.status() != TaskStatus.DONE) {
            return true;
        }

        boolean hasAssignee = request.assignee() != null
                && !request.assignee().isBlank();

        if (!hasAssignee) {
            String message = context.getDefaultConstraintMessageTemplate();

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("assignee").addConstraintViolation();
        }
        return hasAssignee;
    }
}

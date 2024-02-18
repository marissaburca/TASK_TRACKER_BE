package marissaburca.TASK_TRACKER_BE.payloads.task;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import marissaburca.TASK_TRACKER_BE.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskDTO(@NotEmpty(message = "Title field must not be empty") String title, String description,
                      @NotNull(message = "Name field must not be empty") LocalDate date,
                      @NotNull(message = "Name field must not be empty") LocalTime time) {
}

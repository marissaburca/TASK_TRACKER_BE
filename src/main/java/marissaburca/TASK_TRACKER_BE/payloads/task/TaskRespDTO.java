package marissaburca.TASK_TRACKER_BE.payloads.task;

import marissaburca.TASK_TRACKER_BE.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record TaskRespDTO(long id, String title,
                          String description,
                          LocalDate date,
                          LocalTime time,
                          TaskStatus status
                          ) {
}

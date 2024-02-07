package marissaburca.TASK_TRACKER_BE.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timing) {
}

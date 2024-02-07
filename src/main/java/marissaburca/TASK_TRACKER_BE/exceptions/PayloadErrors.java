package marissaburca.TASK_TRACKER_BE.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class PayloadErrors {
    private String message;
    private LocalDateTime timing;
}

package marissaburca.TASK_TRACKER_BE.payloads.note;

import jakarta.validation.constraints.NotEmpty;

public record NoteDTO(@NotEmpty(message= "Input field cannot be empty") String note) {
}

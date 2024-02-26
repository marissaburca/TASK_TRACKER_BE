package marissaburca.TASK_TRACKER_BE.payloads.user;

import jakarta.validation.constraints.NotEmpty;

public record UserPswdDTO(@NotEmpty(message = "Password field should not be empty! ") String password) {
}

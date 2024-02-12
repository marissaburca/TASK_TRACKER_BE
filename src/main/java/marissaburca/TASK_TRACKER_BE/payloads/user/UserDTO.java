package marissaburca.TASK_TRACKER_BE.payloads.user;

import jakarta.validation.constraints.NotEmpty;
import marissaburca.TASK_TRACKER_BE.entities.Gender;

public record UserDTO(@NotEmpty(message = "Name field must not be empty") String name,
                      @NotEmpty(message = "Surname field must not be empty") String surname,
                      @NotEmpty(message = "Username field must not be empty") String username, Gender gender,
                      Long avatarId, @NotEmpty(message = "Email field must not be empty") String email,
                      @NotEmpty(message = "Password field must not be empty") String password


) {
}

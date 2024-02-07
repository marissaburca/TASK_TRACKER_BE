package marissaburca.TASK_TRACKER_BE.controllers;

import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsers () {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById ( @PathVariable long userId ) {
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser ( @PathVariable long userId, @RequestBody User modifiedUserPayload ) {
        return userService.findByIdAndUpdate(userId, modifiedUserPayload);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser ( @PathVariable long userId ) {
        userService.findByIdAndDelete(userId);
    }
}

package marissaburca.TASK_TRACKER_BE.controllers;

import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserPswdDTO;
import marissaburca.TASK_TRACKER_BE.services.AuthService;
import marissaburca.TASK_TRACKER_BE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PutMapping
    public User updateUser ( @RequestBody UserDTO modifiedUserPayload, @AuthenticationPrincipal User loggedUser ) {
        return userService.findByAuthAndUpdate(modifiedUserPayload, loggedUser);
    }
    @PutMapping("/updatePassword")
    public void updatePswd( @RequestBody UserPswdDTO pswd, @AuthenticationPrincipal User loggedUser ) {
       userService.updatePassword(pswd, loggedUser);
    }

    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser ( @AuthenticationPrincipal User loggedUser) {
        userService.findAndDelete(loggedUser);
    }
}

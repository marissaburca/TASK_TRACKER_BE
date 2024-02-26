package marissaburca.TASK_TRACKER_BE.controllers;

import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.payloads.user.*;
import marissaburca.TASK_TRACKER_BE.security.JWTTools;
import marissaburca.TASK_TRACKER_BE.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTTools jwtTools;

    @PostMapping("/login")
    public UserLoginRespDTO login( @RequestBody UserLoginDTO body) {
        String accessToken = authService.logUser(body);
        String id = jwtTools.extractIdFromToken(accessToken);
        return new UserLoginRespDTO(accessToken,id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRespDTO createUser( @RequestBody @Validated UserDTO userPayload, BindingResult validation) throws BadRequest {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequest("Invalid request payload " + validation.getErrorCount());
        } else {
            return authService.save(userPayload);
        }
    }
}

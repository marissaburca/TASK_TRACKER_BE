package marissaburca.TASK_TRACKER_BE.services;

import marissaburca.TASK_TRACKER_BE.entities.Role;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.exceptions.Unauthorized;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserLoginDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserRespDTO;
import marissaburca.TASK_TRACKER_BE.repositories.UserDAO;
import marissaburca.TASK_TRACKER_BE.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserDAO userDAO;

    public String logUser ( UserLoginDTO body ) {
        System.out.println(body.email() + body.password());
        User user = userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new Unauthorized("Invalid credentials!");
        }
    }

    public UserRespDTO save ( UserDTO body ) throws BadRequest {
        try {
            Optional<User> found = userDAO.findByEmail(body.email());
            if (!found.isPresent()) {
                User newUser = new User();

                newUser.setName(body.name());
                newUser.setSurname(body.surname());
                newUser.setUsername(body.username());
                newUser.setGender(body.gender());
                newUser.setAvatar(body.avatar());
                //AFTER CREATION OF FIRST ADMIN SWITCH ROLE INTO "USER"
                newUser.setRole(Role.ADMIN);
                newUser.setEmail(body.email());
                newUser.setPassword(bcrypt.encode(body.password()));

                userDAO.save(newUser);
                return new UserRespDTO(newUser.getId());
            } else {
                throw new BadRequest("Email " + body.email() + " already in use!");
            }
        } catch (RuntimeException e) {
            throw new BadRequest("Email " + body.email() + " already in use!");
        }

    }

}

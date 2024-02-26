package marissaburca.TASK_TRACKER_BE.services;

import marissaburca.TASK_TRACKER_BE.entities.Avatar;
import marissaburca.TASK_TRACKER_BE.entities.Role;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.exceptions.Unauthorized;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserLoginDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserPswdDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserRespDTO;
import marissaburca.TASK_TRACKER_BE.repositories.UserDAO;
import marissaburca.TASK_TRACKER_BE.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    @Autowired
    private AvatarService avatarService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String me;

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

        Optional<User> found = userDAO.findByEmail(body.email());
        if (found.isPresent()) {
            throw new BadRequest("Email " + body.email() + " already in use!");
        }
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.username());
        newUser.setGender(body.gender());
        Avatar avatar = avatarService.findById(body.avatarId());
        newUser.setAvatar(avatar);
        //AFTER CREATION OF FIRST ADMIN SWITCH ROLE INTO "USER"
        newUser.setRole(Role.ADMIN);
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser = userDAO.save(newUser);
        sendWelcomeEmail(newUser);

        return new UserRespDTO(newUser.getId());

    }
    private void sendWelcomeEmail(User user) {
        // Creating an object SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(me);
        message.setTo(user.getEmail());
        System.out.println(user.getEmail());
        message.setSubject("Welcome to TASK TRACKER 🥳");
        message.setText("Hello " + user.getUsername() + "🤩! Welcome to TASK TRACKER! We are glad to have you on board.");
        // Sending l'email
        mailSender.send(message);
    }
}



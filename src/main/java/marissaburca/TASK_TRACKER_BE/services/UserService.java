package marissaburca.TASK_TRACKER_BE.services;

import marissaburca.TASK_TRACKER_BE.entities.Avatar;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.exceptions.NotFound;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserDTO;
import marissaburca.TASK_TRACKER_BE.payloads.user.UserPswdDTO;
import marissaburca.TASK_TRACKER_BE.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AvatarService avatarService;
    @Autowired
    @Lazy
    private PasswordEncoder bcrypt;
    @Value("${spring.mail.username}")
    String me;

    public List<User> getAllUsers () {
        return userDAO.findAll();
    }

    public User findById ( long id ) {
        return userDAO.findById(id).orElseThrow(() -> new NotFound(id));
    }

    public User findByAuthAndUpdate ( UserDTO body ,User loggedUser) {
        loggedUser.setName(body.name());
        loggedUser.setSurname(body.surname());
        loggedUser.setUsername(body.username());
        loggedUser.setGender(body.gender());
        Avatar avatar = avatarService.findById(body.avatarId());
        loggedUser.setAvatar(avatar);
        loggedUser.setEmail(body.email());
        return userDAO.save(loggedUser);
    }
    public void updatePassword ( UserPswdDTO payload, User loggedUser){
        if (bcrypt.matches(payload.password(), loggedUser.getPassword())){
            throw new BadRequest("New password cannot correspond to the old one.");
        }
        loggedUser.setPassword(bcrypt.encode(payload.password()));
    }


    public void findAndDelete ( User loggedUser ) {
        sendGoodbyeEmail(loggedUser);
        userDAO.delete(loggedUser);
    }

    public User findByEmail ( String email ) throws NotFound {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFound("User with email '" + email + "' not found!"));
    }
    private void sendGoodbyeEmail(User user) {
        // Creating an object SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(me);
        message.setTo(user.getEmail());
        System.out.println(user.getEmail());
        message.setSubject("We're sorry you're leaving us!'");
        message.setText("Dear " + user.getUsername() + ", we will miss you, but we hope that our app has been useful to you and that, perhaps, we will see you again soon.");
        // Send email
        mailSender.send(message);
    }

}

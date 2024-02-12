package marissaburca.TASK_TRACKER_BE.services;

import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.NotFound;
import marissaburca.TASK_TRACKER_BE.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String me;

    public List<User> getAllUsers () {
        return userDAO.findAll();
    }

    public User findById ( long id ) {
        return userDAO.findById(id).orElseThrow(() -> new NotFound(id));
    }

    public User findByIdAndUpdate ( long id, User body ) {
        User found = this.findById(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setUsername(body.getUsername());
        found.setGender(body.getGender());
        found.setAvatar(body.getAvatar());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        found.setTasks(body.getTasks());
        return userDAO.save(found);
    }

    public void findByIdAndDelete ( long id ) {
        User found = this.findById(id);
        sendGoodbyeEmail(found);
        userDAO.delete(found);
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
        // Send l'email
        mailSender.send(message);
    }

}

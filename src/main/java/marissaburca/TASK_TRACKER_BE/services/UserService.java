package marissaburca.TASK_TRACKER_BE.services;

import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.NotFound;
import marissaburca.TASK_TRACKER_BE.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

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
        return userDAO.save(found);
    }

    public void findByIdAndDelete ( long id ) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByEmail ( String email ) throws NotFound {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFound("User with email '" + email + "' not found!"));
    }
}

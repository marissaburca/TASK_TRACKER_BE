package marissaburca.TASK_TRACKER_BE.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import marissaburca.TASK_TRACKER_BE.entities.Avatar;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.NotFound;
import marissaburca.TASK_TRACKER_BE.repositories.AvatarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class AvatarService {

    @Autowired
    private AvatarDAO avatarDAO;

    public List<Avatar> getAllAvatars() {
        return avatarDAO.findAll();
    }
    public Avatar findById ( Long id ) {
        return avatarDAO.findById(id).orElseThrow(()-> new NotFound(id));
    }


}

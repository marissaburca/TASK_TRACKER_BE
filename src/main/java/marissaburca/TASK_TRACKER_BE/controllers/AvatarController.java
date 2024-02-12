package marissaburca.TASK_TRACKER_BE.controllers;

import marissaburca.TASK_TRACKER_BE.entities.Avatar;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    @Autowired
    private AvatarService avatarService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Avatar> getAllAvatars () {
        return avatarService.getAllAvatars();
    }
    @GetMapping("/{id}")
    public Avatar getAvatar(@PathVariable Long id){
        return avatarService.findById(id);
    }
}
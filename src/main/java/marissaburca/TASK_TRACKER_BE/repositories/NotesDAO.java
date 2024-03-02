package marissaburca.TASK_TRACKER_BE.repositories;

import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotesDAO extends JpaRepository<Note, Long> {
    List <Note> findByUser( User user);
}

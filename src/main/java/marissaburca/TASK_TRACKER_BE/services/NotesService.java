package marissaburca.TASK_TRACKER_BE.services;

import marissaburca.TASK_TRACKER_BE.entities.Task;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.entities.Note;
import marissaburca.TASK_TRACKER_BE.exceptions.NotFound;
import marissaburca.TASK_TRACKER_BE.payloads.note.NoteDTO;
import marissaburca.TASK_TRACKER_BE.payloads.note.NoteRespDTO;
import marissaburca.TASK_TRACKER_BE.payloads.task.TaskRespDTO;
import marissaburca.TASK_TRACKER_BE.repositories.NotesDAO;
import marissaburca.TASK_TRACKER_BE.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotesService {
    @Autowired
    private NotesDAO notesDAO;
    @Autowired
    private UserDAO userDAO;

    public Note findById ( long id ) {
        return notesDAO.findById(id).orElseThrow(() -> new NotFound(id));
    }

    public List<Note> getAllNotesForCurrentUser( User loggedUser) {
        String email = loggedUser.getEmail();
        User user = userDAO.findByEmail(email).orElseThrow(() -> new NotFound("User with email '" + email + "' not found!"));

        return notesDAO.findByUser(user);
    }
    public NoteRespDTO saveNote ( NoteDTO body , User loggedUser) {
        String email = loggedUser.getEmail();
        // RECOVER USER FROM DB USING USERNAME(email)
        User user = userDAO.findByEmail(email).orElseThrow(() -> new NotFound("User with email '" + email + "' not found!"));
        Note newNote = new Note();
        newNote.setNote(body.note());
        newNote.setUser(user);
        Note savedNote = notesDAO.save(newNote);
        return new NoteRespDTO(savedNote.getId(), savedNote.getNote());
    }
     public Note updateNote(long id, Note body){
        Note found = this.findById(id);
        found.setNote(body.getNote());
        return notesDAO.save(found);
     }
    public void findByIdAndDelete ( long id ) {
        Note found = this.findById(id);
        notesDAO.delete(found);
    }

}

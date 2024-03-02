package marissaburca.TASK_TRACKER_BE.controllers;
import marissaburca.TASK_TRACKER_BE.entities.Note;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.payloads.note.NoteDTO;
import marissaburca.TASK_TRACKER_BE.payloads.note.NoteRespDTO;
import marissaburca.TASK_TRACKER_BE.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NotesService notesService;


    @GetMapping
    public List<Note> getNotes( @AuthenticationPrincipal User loggedUser) {
        return notesService.getAllNotesForCurrentUser(loggedUser);
    }

    @GetMapping("/{noteId}")
    public Note getNoteById ( @PathVariable long noteId ) {
        return notesService.findById(noteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteRespDTO saveNote ( @RequestBody @Validated NoteDTO payload, BindingResult validation, @AuthenticationPrincipal User loggedUser) throws BadRequest {
        if (validation.hasErrors()) {
            throw new BadRequest("Errors in validation " + validation.getAllErrors());
        } else {
            return notesService.saveNote(payload, loggedUser);
        }
    }

    @PutMapping("/{noteId}")
    public Note updateNote ( @PathVariable long noteId, @RequestBody Note editedNotePayload ) {
        return notesService.updateNote(noteId, editedNotePayload);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote( @PathVariable long noteId ) {
        notesService.findByIdAndDelete(noteId);
    }
}

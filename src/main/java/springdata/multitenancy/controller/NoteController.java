package springdata.multitenancy.controller;

import org.springframework.http.MediaType;
import springdata.multitenancy.entity.Note;
import springdata.multitenancy.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/{tenantId}/notes", method = RequestMethod.POST)
    public ResponseEntity<Note> createNote(@PathVariable final String tenantId, @RequestBody Note note) {
        Note created = noteService.createNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @RequestMapping(value = "/{tenantId}/notes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Note> getNote(@PathVariable final String tenantId, @PathVariable Long id) {
        Note note = noteService.findNote(id);
        return ResponseEntity.ok(note);
    }

    @RequestMapping(value = "/{tenantId}/notes", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Note>> getAllNotes(@PathVariable final String tenantId) {
        Iterable<Note> notes = noteService.findAllNotes();
        return ResponseEntity.ok(notes);
    }
}

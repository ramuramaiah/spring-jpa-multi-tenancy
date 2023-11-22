package springdata.multitenancy.service;

import org.springframework.stereotype.Service;
import springdata.multitenancy.entity.Note;
import springdata.multitenancy.repository.NoteRepository;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note findNote(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public Iterable<Note> findAllNotes() {
        return noteRepository.findAll();
    }
}

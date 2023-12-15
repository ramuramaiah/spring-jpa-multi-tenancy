package springdata.multitenancy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.multitenancy.dao.NoteDAO;
import springdata.multitenancy.entity.Note;

@Service
public class NoteService {

    @Autowired
    private NoteDAO noteDao;

    public Note createNote(Note note) {
        return noteDao.save(note);
    }

    public Note findNote(Long id) {
        return noteDao.findById(id).orElseThrow();
    }

    public Iterable<Note> findAllNotes() {
        return noteDao.findAll();
    }
}

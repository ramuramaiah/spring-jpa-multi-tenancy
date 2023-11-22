package springdata.multitenancy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springdata.multitenancy.entity.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}
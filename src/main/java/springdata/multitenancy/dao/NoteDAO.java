package springdata.multitenancy.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springdata.multitenancy.entity.Note;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Note save(Note note) {
        try {
            Long id = (Long)this.sessionFactory.getCurrentSession().save(note);
            return findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred during saving user ", e);
        }
    }

    @Transactional
    public Optional<Note> findById(Long id) {
        Query<Note> query = this.sessionFactory.getCurrentSession()
                .createQuery("from " + Note.class.getName()+ " where id = :id");
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    @Transactional
    public List<Note> findAll() {
        Query<Note> query = this.sessionFactory.getCurrentSession()
                .createQuery("from " + Note.class.getName());
        return query.list();
    }
}

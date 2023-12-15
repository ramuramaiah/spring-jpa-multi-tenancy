package springdata.multitenancy.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springdata.multitenancy.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public User save(User user) {
        try {
            Long id = (Long)this.sessionFactory.getCurrentSession().save(user);
            return findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred during saving user ", e);
        }
    }

    @Transactional
    public Optional<User> findById(Long id) {
        Query<User> query = this.sessionFactory.getCurrentSession()
                .createQuery("from " + User.class.getName()+ " where id = :id");
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        Query<User> query = this.sessionFactory.getCurrentSession()
                .createQuery("from " + User.class.getName()+ " where username = :username");
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }

    @Transactional
    public List<User> findAll() {
        Query<User> query = this.sessionFactory.getCurrentSession()
                .createQuery("from " + User.class.getName());
        return query.list();
    }
}

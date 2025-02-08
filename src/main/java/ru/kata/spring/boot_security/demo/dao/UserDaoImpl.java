package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        user.setRoles(Collections.singleton(new Role(2L)));
        user.setPassword(user.getPassword());
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "FROM User", User.class).getResultList();
    }

    @Override
    public void updateUser(Long id, User user) {

        if (user != null) {
            entityManager.merge(user);
        }
    }
}

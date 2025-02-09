package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user, Long[] roles) {
        user.setRoles(newRoleSet(roles));
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

    private Set<Role> newRoleSet(Long[] roles) {


        Set<Role> roleSet = new HashSet<>();

        for (Long l : roles) {
            roleSet.add(new Role(l));
        }
        return roleSet;
    }
}

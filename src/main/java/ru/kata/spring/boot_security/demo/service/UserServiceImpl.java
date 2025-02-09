package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {

        List<User> users = userDao.getAll();
        users.forEach(user -> user.getRoles().forEach(role ->
                System.out.println("РОЛЬ ---- " + role.getName())));
        return userDao.getAll();
    }

    public void saveUser(User user, Long[] roles) {
        userDao.add(user,roles);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public void updateUser(Long id, User user) {
        userDao.updateUser(id, user);
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User findUserById(Long id) {
       return userDao.findById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByName(username);
    }
}


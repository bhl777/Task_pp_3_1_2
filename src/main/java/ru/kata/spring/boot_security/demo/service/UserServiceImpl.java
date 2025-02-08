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

        return userDao.getAll();
    }

    public void saveUser(User user) {
        userDao.add(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public void updateUser(Long id, User user) {
        userDao.updateUser(id, user);
    }
}


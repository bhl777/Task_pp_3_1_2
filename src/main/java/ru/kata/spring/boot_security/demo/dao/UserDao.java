package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    void add(User user, Long[] roles);
    void deleteUser(Long id);
    void updateUser(Long id, User user);
    User findByName(String name);
    User findById(Long id);
}

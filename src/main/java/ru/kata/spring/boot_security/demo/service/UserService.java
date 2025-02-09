package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    void saveUser(User user, Long[] roles);
    void deleteUser(Long id);
    void updateUser(Long id, User user);
    User getUserById(Long id);
    User findUserById(Long id);
    User findByUsername(String username);
}

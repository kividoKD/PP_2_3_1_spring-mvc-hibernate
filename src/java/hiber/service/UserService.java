package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User show(Long id);

    void update(Long id, User user);

    void delete(Long id);
}

package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    List<User> listUsers();

    User showUser(Long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}

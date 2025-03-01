package org.sidorov.workroom.datalayer.data;

import org.sidorov.workroom.datalayer.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    void deleteUserById(int userId);

    List<User> getListUsers();

    List<User> getListUsersByStatus(String status);

    User getUserByLogin(String login);

    User getUserById(int userId);

    void updateUser(User user);

    void setAuthorized(boolean isAuthorized, int userId);

    void setStatus(String status, int userId);
}

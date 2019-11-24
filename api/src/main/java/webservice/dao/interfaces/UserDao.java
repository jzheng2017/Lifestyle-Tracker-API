package webservice.dao.interfaces;

import webservice.dto.User;

import java.util.List;

public interface UserDao {

    User getUser();

    void updateUser();

    void deleteUser();

    void insertUser(User user);

    List<User> getAll();
}

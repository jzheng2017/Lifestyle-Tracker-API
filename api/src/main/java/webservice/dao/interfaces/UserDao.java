package webservice.dao.interfaces;

import webservice.dto.User;

import java.util.List;

public interface UserDao {

    User getUser(int userId);

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    boolean insertUser(User user);

    List<User> getAll();
}

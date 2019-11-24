package webservice.services;

import webservice.dao.interfaces.UserDao;
import webservice.dto.User;

import javax.inject.Inject;
import java.util.List;

public class UserService {

    private UserDao userDao;

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public User getUser(int userId) {
        return userDao.getUser(userId);
    }
}

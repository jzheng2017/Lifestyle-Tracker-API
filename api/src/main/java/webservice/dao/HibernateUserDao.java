package webservice.dao;

import webservice.dao.interfaces.UserDao;
import webservice.dto.User;

import javax.enterprise.inject.Default;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Default
public class HibernateUserDao implements UserDao {
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}

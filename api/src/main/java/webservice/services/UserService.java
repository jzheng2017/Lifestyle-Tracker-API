package webservice.services;

import webservice.dao.interfaces.UserDao;
import webservice.dto.User;

import javax.inject.Inject;

public class UserService {

    private UserDao userDao;

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public User getUser(){
        return this.userDao.getUser();
    }
}

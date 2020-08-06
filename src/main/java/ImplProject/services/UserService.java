package ImplProject.services;

import ImplProject.daos.UserDao;
import ImplProject.entities.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    private static UserService userService;

    private UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User create(User t) {
        return userDao.create(t);
    }

    public User read(int id) {
        return userDao.read(id);
    }

    public void update(User t) {
        userDao.update(t);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public List<User> readAll() {
        return userDao.readAll();
    }
}

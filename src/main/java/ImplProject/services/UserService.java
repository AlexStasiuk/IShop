package ImplProject.services;

import ImplProject.daos.UserDao;
import ImplProject.entities.User;

import java.util.List;
import java.util.Optional;

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

    public User create(String email, String name, String surname, String password) {
        return userDao.create(User.builder()
                .setEmail(email)
                .setName(name)
                .setPassword(password)
                .setSurname(surname)
                .build());
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

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }
    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userDao.getByEmail(email).filter(user -> user.getPassword().equals(password));
    }
}

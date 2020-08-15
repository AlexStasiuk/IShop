package ImplProject.daos;

import ImplProject.ConnectionUtil;
import ImplProject.entities.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements CRUD<User> {
    public static final String INSERT_INTO =
            "INSERT INTO users(name, surname, email, role, password) values (?, ?, ?, ?, ?)";
    public static final String SELECT_BY_ID = "SELECT * from users where id = ?";
    public static final String SELECT_ALL = "SELECT * FROM users";
    public static final String UPDATING_BY_ID = "UPDATE users SET name = ?, surname = ?, role = ?, email = ?, password = ? where id = ?";
    public static final String DELETE_BY_ID = "DELETE from users where id = ?";
    private Connection connection;
    public static final String SELECT_BY_EMAIL = "select * from users where email = ?";
    private static final Logger LOG = Logger.getLogger(UserDao.class);

    public UserDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public User create(User user) {
        try {
            LOG.info("Creating user ...");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));

            LOG.info("User was created");
            return user;
        } catch (SQLException e) {
            LOG.error("Error while creating " + user.toString(), e);
            throw new RuntimeException("Error while inserting user");
        }

    }

    @Override
    public User read(int id) {
        try {
            LOG.info(String.format("reading user with id = %d", id));
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = User.of(resultSet);
            LOG.info(String.format("User with id %d was read", id));
            return user;
        } catch (SQLException e) {

            LOG.error("Error while getting user by id = " + id, e);
            throw new RuntimeException("Error while getting user by id = " + id);
        }
    }

    @Override
    public void update(User user) {
        try {
            LOG.info("trying to update user with id = " + user.getId());
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATING_BY_ID);
            preparedStatement.setObject(1, user.getName());
            preparedStatement.setObject(2, user.getSurname());
            preparedStatement.setObject(3, user.getRole());
            preparedStatement.setObject(4, user.getEmail());
            preparedStatement.setObject(5, user.getPassword());
            preparedStatement.setObject(6, user.getId());

            preparedStatement.executeUpdate();
            LOG.info("user was updated");
        } catch (SQLException e) {
            LOG.error("Error while updating user" + user.toString(), e);
            e.printStackTrace();
            throw new RuntimeException("Error while updating users");
        }

    }

    @Override
    public void delete(int id) {
        try {
            LOG.info("trying delete user with id" + id);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            LOG.info("User with id = " + id + " deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Error while deleting user by id = " + id, e);
            throw new RuntimeException("Error while deleting user by id = " + id);
        }
    }

    @Override
    public List<User> readAll() {
        try {
            LOG.info("trying to read all users");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }
            LOG.info("All users were read");
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Error while selecting all users", e);
            throw new RuntimeException("Error while selecting all users");
        }
    }

    public Optional<User> getByEmail(String email) {
        try {
            LOG.info("trying to get optional of user with email = " + email);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setObject(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                LOG.info("get optional user with email = " + email);
                return Optional.of(User.of(resultSet));
            }
            LOG.info("failed to get optional of user with email = " + email);
            return Optional.empty();
        } catch (SQLException e) {
            LOG.error("Error while getting user by email=" + email, e);
            throw new RuntimeException("Error while getting user by email= " + email);
        }
    }
}


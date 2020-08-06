package ImplProject.daos;

import ImplProject.ConnectionUtil;
import ImplProject.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CRUD<User> {
    public static final String INSERT_INTO =
            "INSERT INTO users(name, surname, email, role) values (?, ?, ?, ?)";
    public static final String SELECT_BY_ID = "SELECT * from users where id = ?";
    public static final String SELECT_ALL = "SELECT * FROM users";
    public static final String UPDATING_BY_ID = "UPDATE users SET name = ?, surname = ?, role = ?, email = ? where id = ?";
    public static final String DELETE_BY_ID = "DELETE from users where id = ?";
    private Connection connection;

    public UserDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public User create(User user) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));


            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while inserting user");
        }

    }

    @Override
    public User read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = User.of(resultSet);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting user by id = " + id);
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATING_BY_ID);
            preparedStatement.setObject(1, user.getName());
            preparedStatement.setObject(2, user.getSurname());
            preparedStatement.setObject(3, user.getRole());
            preparedStatement.setObject(4, user.getEmail());
            preparedStatement.setObject(5, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while updating users");
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while deleting user by id = " + id);
        }
    }

    @Override
    public List<User> readAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while selecting all users");
        }
    }
}

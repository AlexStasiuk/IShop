package ImplProject.daos;

import ImplProject.ConnectionUtil;
import ImplProject.entities.Bucket;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDao implements CRUD<Bucket> {
    private static final String INSERT_INTO = "INSERT into buckets (user_id, product_id, dateTime) values(?,?,?)";
    private static final String SELECT_BY_ID = "Select * from buckets where id = ?";
    private static final String DELETE_BY_ID = "DELETE from buckets where id = ?";
    private static final String UPDATE_BY_ID = "UPDATE buckets SET user_id = ?, product_id = ?, dateTime = ? where id = ?";
    private static final String SELECT_ALL = "SELECT * from buckets";
    private static final String READ_ALL_BY_USER_ID = "select * from buckets where user_id = ?";
    private Connection connection;
    public BucketDao() {
        connection = ConnectionUtil.getConnection();
    }


    @Override
    public Bucket create(Bucket bucket) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, bucket.getUser_id());
            preparedStatement.setObject(2, bucket.getProduct_id());
            preparedStatement.setObject(3, bucket.getDateTime());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            bucket.setId(generatedKeys.getInt(1));
            return bucket;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while inserting bucket");
        }


    }

    @Override
    public Bucket read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Bucket bucket = Bucket.of(resultSet);

            return bucket;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read bucket by id");
        }

    }

    @Override
    public void update(Bucket bucket) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setObject(1, bucket.getUser_id());
            preparedStatement.setObject(2, bucket.getProduct_id());
            preparedStatement.setObject(3, bucket.getDateTime());
            preparedStatement.setObject(4, bucket.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            throw new RuntimeException("Error while deleting bucket");
        }
    }

    @Override
    public List<Bucket> readAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Bucket> buckets = new ArrayList<>();
            while (resultSet.next()) {
                buckets.add(Bucket.of(resultSet));
            }
            return buckets;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while select all buckets");
        }
    }

    public List<Bucket> readAllByUserId(int userId) {

        List<Bucket> bucketRecords = new ArrayList<>();
        try {
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(READ_ALL_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                bucketRecords.add(Bucket.of(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bucketRecords;
    }
}

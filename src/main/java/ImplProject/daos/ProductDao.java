package ImplProject.daos;

import ImplProject.ConnectionUtil;
import ImplProject.entities.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDao implements CRUD<Product> {
    private static final String INSERT_INTO = "INSERT into products (name, description, price) values(?,?,?)";
    private static final String SELECT_BY_ID = "Select * from products where id = ?";
    private static final String DELETE_BY_ID = "DELETe from products where id = ?";
    private static final String UPDATE_BY_ID = "UPDATE products SET name = ?, description = ?, price = ? where id = ?";
    private static final String SELECT_ALL = "SELECT * from products";
    private static  String READ_ALL_IN = "select * from products where id in ";

    private Connection connection;

    public ProductDao() {
        connection = ConnectionUtil.getConnection();
    }

    @Override
    public Product create(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, product.getName());
            preparedStatement.setObject(2, product.getDescription());
            preparedStatement.setObject(3, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            product.setId(generatedKeys.getInt(1));
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while inserting product");
        }


    }

    @Override
    public Product read(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Product product = Product.of(resultSet);

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read product by id");
        }

    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setObject(1,product.getName());
            preparedStatement.setObject(2,product.getDescription());
            preparedStatement.setObject(3,product.getPrice());
            preparedStatement.setObject(4,product.getId());
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
            throw new RuntimeException("Error while deleting product");
        }

    }

    @Override
    public List<Product> readAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(Product.of(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while select all products");
        }
    }

    public List<Product> readByIds(Set<Integer> productIds) {
        PreparedStatement preparedStatement;
        List<Product> productRecords = new ArrayList<>();
        try {

            String ids = productIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            String query = String.format("%s (%s)", READ_ALL_IN, ids);
            preparedStatement = connection.prepareStatement(query);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                productRecords.add(Product.of(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productRecords;
    }
}

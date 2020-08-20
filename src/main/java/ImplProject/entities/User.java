package ImplProject.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String email;
    private String name;
    private String surname;
    private String role;


    private String password;

    public User() {
    }


    public static class Builder {
        private int id;
        private String email;
        private String name;
        private String surname;
        private String role;
        private String password;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setRole(role);
            user.setPassword(password);

            return user;
        }
    }
    public static Builder builder() {
        return new Builder();
    }


    public static User of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            return User.builder()
                    .setId(id)
                    .setEmail(email)
                    .setName(name)
                    .setSurname(surname)
                    .setRole(role)
                    .setPassword(password)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role == null){
           this.role = UserRole.USER.name();
        }
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

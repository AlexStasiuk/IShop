package ImplProject.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;


public class Bucket {
    private int id;
    private int user_id;
    private int product_id;
    private Date dateTime;

    public static Bucket of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int user_id = resultSet.getInt("user_id");
            int product_id = resultSet.getInt("product_id");
            Date dateTime = resultSet.getDate("datetime");
            return new Bucket(id, user_id, product_id, dateTime);
        } catch (SQLException e) {
            throw new RuntimeException("Error while product of");
        }
    }

    public Bucket(int id, int user_id, int product_id, Date dateTime) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bucket)) return false;
        Bucket bucket = (Bucket) o;
        return getId() == bucket.getId() &&
                getUser_id() == bucket.getUser_id() &&
                getProduct_id() == bucket.getProduct_id() &&
                getDateTime().equals(bucket.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getProduct_id(), getDateTime());
    }
}

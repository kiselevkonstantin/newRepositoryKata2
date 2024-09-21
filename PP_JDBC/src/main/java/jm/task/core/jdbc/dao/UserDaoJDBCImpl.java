package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50)," +
                    "last_name VARCHAR(50)," +
                    "age INT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = Util.getConnection()
                .prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            ps.setString(1, name); //ВЕРОЯТНАЯ ОШИБКА В НОМЕРЕ КОЛОНКИ
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void removeUserById(long id) {
        try (PreparedStatement ps = Util.getConnection()
                .prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (ResultSet rs = Util.getConnection().createStatement().executeQuery(query)) {
            while (rs.next()) {
                User user = new User(rs.getString("name"),
                                        rs.getString("last_name"),
                                        rs.getByte("age"));
                user.setId(rs.getLong("id"));
                resultList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

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
        Util.connection();
        String execute = "CREATE TABLE IF NOT EXISTS `db_kata_1_1_4`.`users` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `lastName` VARCHAR(45) NOT NULL," +
                "  `age` INT NOT NULL," +
                "  PRIMARY KEY (`id`));";
        try(PreparedStatement statement = Util.createStatement(execute)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Util.close();
    }

    public void dropUsersTable() {
        Util.connection();
        String execute = "DROP TABLE IF EXISTS `db_kata_1_1_4`.`users`;";
        try(PreparedStatement statement = Util.createStatement(execute)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Util.close();
    }

    public void saveUser(String name, String lastName, byte age) {
        Util.connection();
        String execute = "INSERT INTO `db_kata_1_1_4`.`users` (" +
                "name, lastName, age) VALUES (?, ?, ?);";
        try(PreparedStatement statement = Util.createStatement(execute)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Util.close();
    }

    public void removeUserById(long id) {
        Util.connection();
        String execute = "DELETE FROM `db_kata_1_1_4`.`users` WHERE id = ?;";
        try(PreparedStatement statement = Util.createStatement(execute)) {
            statement.setInt(1, (int) id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Util.close();
    }

    public List<User> getAllUsers() {
        Util.connection();
        String execute = "SELECT * FROM `db_kata_1_1_4`.`users`";
        try(PreparedStatement statement = Util.createStatement(execute)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        (byte) resultSet.getInt("age")
                );
                user.setId((long) resultSet.getInt("id"));
                list.add(user);
            }
            Util.close();
            return list;
        } catch (SQLException e) {
            Util.close();
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        Util.connection();
        for (User user : getAllUsers()) {
            removeUserById(user.getId());
        }
        Util.close();
    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl table = new UserServiceImpl();
        table.createUsersTable();
        for (int i = 0; i < 4; i++) {
            User user = new User("q" + i, "Q" + i * 10, (byte) i);
            table.saveUser(user.getName(), user.getLastName(), user.getAge());
        }
        table.getAllUsers().forEach(System.out::println);
        table.cleanUsersTable();
        table.dropUsersTable();
    }
}

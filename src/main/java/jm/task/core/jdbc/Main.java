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
        table.saveUser("qq", "QQ", (byte) 4);
        table.dropUsersTable();
    }
}

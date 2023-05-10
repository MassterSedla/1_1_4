package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl table = new UserServiceImpl();
        table.createUsersTable();
        table.saveUser("sss", "213", (byte) 7);
        table.saveUser("32e", "213", (byte) 7);
        table.saveUser("vbth", "213", (byte) 7);
        table.removeUserById(1);
        table.cleanUsersTable();
        table.getAllUsers().forEach(System.out::println);
        table.dropUsersTable();
    }
}

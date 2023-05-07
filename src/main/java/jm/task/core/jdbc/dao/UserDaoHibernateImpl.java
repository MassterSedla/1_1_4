package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS `db_kata_1_1_4`.`users` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NOT NULL," +
                    "  `lastName` VARCHAR(45) NOT NULL," +
                    "  `age` INT NOT NULL," +
                    "  PRIMARY KEY (`id`));";

            session.createNativeQuery(sql, User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS `db_kata_1_1_4`.`users`;";

            session.createNativeQuery(sql, User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}

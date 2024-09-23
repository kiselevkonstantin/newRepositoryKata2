package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name4", "LastName4", (byte) 52);
        userService.saveUser("Name1", "LastName1", (byte) 24);
        userService.saveUser("Name2", "LastName2", (byte) 21);
        userService.saveUser("Name3", "LastName3", (byte) 53);

        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

package Dao;

import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDao extends Dao {

    private final String getUsersyId = "SELECT * FROM user WHERE username = ?";

    public User getUserById(String username) {
        User u = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getUsersyId);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            rs.next();
            u = new User(rs.getString("username"), rs.getString("password"));
            closeConnection(rs, pst);
        } catch (SQLException ex) {
            System.out.println("The user with given id don't exist. ");
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public boolean isLoggedIn(Scanner sc, UserDao udao) {
        System.out.println("Welcome to Private School JAVA.");
        System.out.println("In order to connect to the DataBase of the school, you must log in.");
        System.out.println("You have 3 tries to put the correct username and password. ");
        System.out.println("Enter your usename :");
        String username = sc.next();
        System.out.println("Enter your password :");
        String password = sc.next();
        User u = new User(username, password);
        int i = 1;
        boolean login = false;
        while (!u.equals(udao.getUserById("messi")) && i < 3) {
            System.out.println("You give wrong username or password.\nPlease enter again the correct.");
            i++;
            System.out.println(i + "nd time.");
            System.out.println("Enter your usename :");
            username = sc.next();
            System.out.println("Enter your password :");
            password = sc.next();
            u = new User(username, password);
        }
        if (u.equals(udao.getUserById("messi")) && i <= 3) {
            login = true;
        }
        return login;
    }
}

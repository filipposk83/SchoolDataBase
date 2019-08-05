package schooldatabase;

import Dao.UserDao;
import java.util.Scanner;

public class SchoolDataBase {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        Menu m = new Menu();
        UserDao udao = new UserDao();
        boolean login = udao.isLoggedIn(inp, udao);
        if (login) {
            System.out.println("Log in successful. Now you have access to the school DataBase.");
            do {
                m.printingMenu(m);
                m.insertMenu(m);
            } while (m.returnToStart());
        } else {
            m.noAccess();
        }
        inp.close();
    }
}

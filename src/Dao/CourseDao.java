package Dao;

import entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDao extends Dao {

    private final String getCourses = "SELECT * FROM course";
    private final String getCourseById = "SELECT * FROM course WHERE cid = ?";
    private final String insertCourse = "INSERT INTO course (ctitle, cstream, ctype, cstart, cend) VALUES (?,?,?,?,?)";

    public List<Course> getCourses() {
        List<Course> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getCourses);
            while (rs.next()) {
                LocalDate startDate = LocalDate.parse(rs.getString("cstart"));
                LocalDate endDate = LocalDate.parse(rs.getString("cend"));
                list.add(new Course(rs.getInt("cid"), rs.getString("ctitle"), rs.getString("cstream"), rs.getString("ctype"), startDate, endDate));
            }
            closeConnection(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Course getCourseById(int cid) {
        Course c = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getCourseById);
            pst.setInt(1, cid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            LocalDate startDate = LocalDate.parse(rs.getString("cstart"));
            LocalDate endDate = LocalDate.parse(rs.getString("cend"));
            c = new Course(rs.getInt("cid"), rs.getString("ctitle"), rs.getString("cstream"), rs.getString("ctype"), startDate, endDate);
            closeConnection(rs, pst);
        } catch (SQLException ex) {
            System.out.println("The course with given id don't exist. ");
            System.out.println(ex.getMessage());
        }
        return c;
    }

    public boolean insertCourse(Course c) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertCourse);
            pst.setString(1, c.getCtitle());
            pst.setString(2, c.getCstream());
            pst.setString(3, c.getCtype());
            pst.setString(4, c.getCstart().toString());
            pst.setString(5, c.getCend().toString());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                inserted = true;
                System.out.println("Successfully insert of Course in the DataBase.");
            } else {
                System.out.println("Course not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return inserted;
    }

    public void printCourses(List<Course> list) {
        for (Course c : list) {
            System.out.println(c.getCid() + ". " + c.getCtitle() + " (" + c.getCstream() + " / " + c.getCtype() + ")  from " + c.getCstart() + " until " + c.getCend());
        }
    }

}

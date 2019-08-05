package Dao;

import entities.Classroom;
import entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassroomDao extends Dao {

    private final String getClassroom = "SELECT * FROM classroom";
    private final String getClassroomById = "SELECT * FROM classroom WHERE clid = ?";
    private final String insertClassroom = "INSERT INTO classroom (cid, sid) VALUES (?,?)";

    public List<Classroom> getClassroom() {
        List<Classroom> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getClassroom);
            while (rs.next()) {
                int cid = rs.getInt(2);
                int sid = rs.getInt(3);
                CourseDao cdao = new CourseDao();
                StudentDao sdao = new StudentDao();
                list.add(new Classroom(rs.getInt(1), cdao.getCourseById(cid), sdao.getStudentById(sid)));
            }
            closeConnection(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(ClassroomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Classroom getClassroomById(int clid) {
        Classroom cl = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getClassroomById);
            pst.setInt(1, clid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int cid = rs.getInt(2);
            int sid = rs.getInt(3);
            CourseDao cdao = new CourseDao();
            StudentDao sdao = new StudentDao();
            cl = new Classroom(rs.getInt(1), cdao.getCourseById(cid), sdao.getStudentById(sid));
            closeConnection(rs, pst);
        } catch (SQLException ex) {
            System.out.println("The classroom with given id don't exist. ");
            System.out.println(ex.getMessage());
        }
        return cl;
    }

    public boolean insertClassroom(Classroom cl) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertClassroom);
            pst.setInt(1, cl.getCourse().getCid());
            pst.setInt(2, cl.getStudent().getSid());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                inserted = true;
                System.out.println("Successfully insert of Classroom in the DataBase.");
            } else {
                System.out.println("Classroom not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return inserted;
    }

    public void printClassroom(List<Classroom> listCl, List<Course> listC) {
        for (Course c : listC) {
            System.out.println(c.getCtitle() + " (" + c.getCstream() + ")");
            for (Classroom cl : listCl) {
                if (c.equals(cl.getCourse())) {
                    System.out.println("   " + cl.getStudent().getSfname() + " " + cl.getStudent().getSlname());
                }
            }
        }
    }

}

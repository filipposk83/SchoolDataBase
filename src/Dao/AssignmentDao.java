package Dao;

import entities.Assignment;
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

public class AssignmentDao extends Dao {

    private final String getAssignments = "SELECT * FROM assignment";
    private final String getAssignmentById = "SELECT * FROM assignment WHERE aid = ?";
    private final String getAssignmentByCourse ="SELECT * from assignment WHERE cid = ?";
    private final String insertAssignment = "INSERT INTO assignment (cid, atitle, adescr, adate) VALUES (?,?,?,?)";

    public List<Assignment> getAssignments() {
        List<Assignment> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAssignments);
            while (rs.next()) {
                int cid = rs.getInt(2);
                LocalDate aDate = LocalDate.parse(rs.getString("adate"));
                CourseDao cdao = new CourseDao();
                list.add(new Assignment(rs.getInt(1), cdao.getCourseById(cid), rs.getString(3), rs.getString(4), aDate));
            }
            closeConnection(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Assignment getAssignmentById(int aid) {
        Assignment a = null;
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentById);
            pst.setInt(1, aid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int cid = rs.getInt("cid");
            CourseDao cdao = new CourseDao();
            LocalDate aDate = LocalDate.parse(rs.getString("adate"));
            a = new Assignment(rs.getInt("aid"), cdao.getCourseById(cid), rs.getString("atitle"), rs.getString("adescr"), aDate);
            closeConnection(rs, pst);
        } catch (SQLException ex) {
            System.out.println("The assignment with given id don't exist. ");
            System.out.println(ex.getMessage());
        }
        return a;
    }
    
    public List<Assignment> getAssignmentByCourse(Course c){
        List<Assignment> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentByCourse);
            pst.setInt(1, c.getCid());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Assignment a = new Assignment();
                a.setAid(rs.getInt("aid"));
                a.setAtitle(rs.getString("atitle"));
                a.setAdescr(rs.getString("adescr"));
                a.setCourse(c);
                LocalDate adate = LocalDate.parse(rs.getString("adate"));
                a.setAdate(adate);
                list.add(a);
            }
            closeConnection(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    public boolean insertAssignment(Assignment a) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignment);
            pst.setInt(1, a.getCourse().getCid());
            pst.setString(2, a.getAtitle());
            pst.setString(3, a.getAdescr());
            pst.setString(4, a.getAdate().toString());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                inserted = true;
                System.out.println("Successfully insert of Assignment in the DataBase.");
            } else {
                System.out.println("Assignment not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return inserted;
    }

    public void printAssignments(List<Assignment> list) {
        for (Assignment a : list) {
            System.out.println(a.getAid() + ". " + a.getAtitle() + " (" + a.getAdescr() + ") at date : " + a.getAdate() + " --> " + a.getCourse().getCtitle());
        }
    }

    public void printAssignmentsPerCourse(List<Assignment> listA, List<Course> listC) {
        for (Course c : listC) {
            System.out.println(c.getCtitle() + ":");
            for (Assignment a : listA) {
                if (c.equals(a.getCourse())) {
                    System.out.println("   " + a.getAtitle() + " (" + a.getAdescr() + ")");
                }
            }
        }
    }

}

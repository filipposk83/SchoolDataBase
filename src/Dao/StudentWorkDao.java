package Dao;

import entities.Assignment;
import entities.StudentWork;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentWorkDao extends Dao {

    private final String getStudentWork = "SELECT * FROM studentwork";
    private final String insertWork = "INSERT INTO studentwork (clid, aid, oral, total) VALUES (?,?,?,?)";

    public List<StudentWork> getStudentWork() {
        List<StudentWork> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getStudentWork);
            while (rs.next()) {
                int clid = rs.getInt(2);
                int aid = rs.getInt(3);
                ClassroomDao cldao = new ClassroomDao();
                AssignmentDao adao = new AssignmentDao();
                list.add(new StudentWork(rs.getInt(1), cldao.getClassroomById(clid), adao.getAssignmentById(aid), rs.getInt(4), rs.getInt(5)));
            }
            closeConnection(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(ClassroomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean insertWork(StudentWork w){
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertWork);
            pst.setInt(1, w.getClassroom().getClid());
            pst.setInt(2, w.getAssignment().getAid());
            pst.setInt(3, w.getOral());
            pst.setInt(4, w.getTotal());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                inserted = true;
                System.out.println("Successfully insert of StudentWork in the DataBase.");
            } else {
                System.out.println("StudentWork not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return inserted;
    
    }

    public void printStudentWork(List<StudentWork> listW, List<Assignment> listA) {
        for (Assignment a : listA) {
            System.out.println(a.getAtitle() + " (" + a.getAdescr() + "/" + a.getCourse().getCtitle() + "):");
            for (StudentWork w : listW) {
                if (a.equals(w.getAssignment())) {
                    System.out.println("   " + w.getClassroom().getStudent().getSfname() + " " + w.getClassroom().getStudent().getSlname()
                            + " ---> " + " (oral mark: " + w.getOral() + " / total mark: " + w.getTotal() + ")");

                }
            }
        }
    }

}

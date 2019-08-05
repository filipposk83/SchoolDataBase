package Dao;

import entities.Assignment;
import entities.Course;
import entities.Trainer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainerDao extends Dao {

    private final String getTrainers = "SELECT * FROM trainer";
    private final String insertTrainer = "INSERT INTO trainer (tfname, tlname, cid, tsubject) VALUES (?,?,?,?)";
    private final String getTrainerByCourse ="SELECT * from trainer WHERE cid = ?";

    public List<Trainer> getTrainers() {
        List<Trainer> list = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTrainers);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                CourseDao cdao = new CourseDao();
                list.add(new Trainer(rs.getInt(1), rs.getString("tfname"), rs.getString("tlname"), cdao.getCourseById(cid), rs.getString("tsubject")));
            }
            closeConnection(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertTrainer(Trainer t) {
        boolean inserted = false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertTrainer);
            pst.setString(1, t.getTfname());
            pst.setString(2, t.getTlname());
            pst.setInt(3, t.getCourse().getCid());
            pst.setString(4, t.getTsubject());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                inserted = true;
                System.out.println("Successfully insert of Trainer in the DataBase.");
            } else {
                System.out.println("Trainer not inserted");
            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            inserted = false;
            System.out.println(ex.getLocalizedMessage());
        }
        return inserted;
    }
    
    public List<Trainer> getTrainerByCourse(Course c){
        List<Trainer> list = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getTrainerByCourse);
            pst.setInt(1, c.getCid());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Trainer t = new Trainer();
                t.setTid(rs.getInt("tid"));
                t.setTfname(rs.getString("tfname"));
                t.setTlname(rs.getString("tlname"));
                t.setTsubject("tsubject");
                t.setCourse(c);
                list.add(t);
            }
            closeConnection(rs, pst);
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    public void printTrainers(List<Trainer> list) {
        for (Trainer t : list) {
            System.out.println(t.getTid() + ". " + t.getTfname() + " " + t.getTlname() + " (" + t.getTsubject() + ")");
        }
    }

    public void printTrainersPerCourse(List<Course> listC, List<Trainer> listT) {
        for (Course c : listC) {
            System.out.println(c.getCtitle() + ":");
            for (Trainer t : listT) {
                if (c.equals(t.getCourse())) {
                    System.out.println("   " + t.getTfname() + " " + t.getTlname() + " (" + t.getTsubject() + ")");
                }
            }
        }
    }

}

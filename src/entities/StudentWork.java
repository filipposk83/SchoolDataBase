package entities;

import java.util.Objects;

public class StudentWork {
    private int wid;
    private Classroom classroom;
    private Assignment assignment;
    private int oral;
    private int total;

    public StudentWork() {
    }

    public StudentWork(Classroom classroom, Assignment assignment, int oral, int total) {
        this.classroom = classroom;
        this.assignment = assignment;
        this.oral = oral;
        this.total = total;
    }

    
    public StudentWork(int wid, Classroom classroom, Assignment assignment, int oral, int total) {
        this.wid = wid;
        this.classroom = classroom;
        this.assignment = assignment;
        this.oral = oral;
        this.total = total;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public int getOral() {
        return oral;
    }

    public void setOral(int oral) {
        this.oral = oral;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.wid;
        hash = 47 * hash + Objects.hashCode(this.classroom);
        hash = 47 * hash + Objects.hashCode(this.assignment);
        hash = 47 * hash + this.oral;
        hash = 47 * hash + this.total;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentWork other = (StudentWork) obj;
        if (this.wid != other.wid) {
            return false;
        }
        if (this.oral != other.oral) {
            return false;
        }
        if (this.total != other.total) {
            return false;
        }
        if (!Objects.equals(this.classroom, other.classroom)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentWork{" + "wid=" + wid + ", classroom=" + classroom + ", assignment=" + assignment + ", oral=" + oral + ", total=" + total + '}';
    }
    
    
}

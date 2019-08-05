package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private int sid;
    private String sfname;
    private String slname;
    private LocalDate sdate;
    private double sfees;

    public Student() {
    }

    public Student(String sfname, String slname, LocalDate sdate, double sfees) {
        this.sfname = sfname;
        this.slname = slname;
        this.sdate = sdate;
        this.sfees = sfees;
    }
    
    

    public Student(int sid, String sfname, String slname, LocalDate sdate, double sfees) {
        this.sid = sid;
        this.sfname = sfname;
        this.slname = slname;
        this.sdate = sdate;
        this.sfees = sfees;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getSlname() {
        return slname;
    }

    public void setSlname(String slname) {
        this.slname = slname;
    }

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public double getSfees() {
        return sfees;
    }

    public void setSfees(double sfees) {
        this.sfees = sfees;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.sid;
        hash = 29 * hash + Objects.hashCode(this.sfname);
        hash = 29 * hash + Objects.hashCode(this.slname);
        hash = 29 * hash + Objects.hashCode(this.sdate);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.sfees) ^ (Double.doubleToLongBits(this.sfees) >>> 32));
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
        final Student other = (Student) obj;
        if (this.sid != other.sid) {
            return false;
        }
        if (Double.doubleToLongBits(this.sfees) != Double.doubleToLongBits(other.sfees)) {
            return false;
        }
        if (!Objects.equals(this.sfname, other.sfname)) {
            return false;
        }
        if (!Objects.equals(this.slname, other.slname)) {
            return false;
        }
        if (!Objects.equals(this.sdate, other.sdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "sid=" + sid + ", sfname=" + sfname + ", slname=" + slname + ", sdate=" + sdate + ", sfees=" + sfees + '}';
    }
    
    
}

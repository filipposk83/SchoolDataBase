package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {
    private int aid;
    private Course course;
    private String atitle;
    private String adescr;
    private LocalDate adate;

    public Assignment() {
    }

    public Assignment(Course course, String atitle, String adescr, LocalDate adate) {
        this.course = course;
        this.atitle = atitle;
        this.adescr = adescr;
        this.adate = adate;
    }

    
    public Assignment(int aid, Course course, String atitle, String adescr, LocalDate adate) {
        this.aid = aid;
        this.course = course;
        this.atitle = atitle;
        this.adescr = adescr;
        this.adate = adate;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getAdescr() {
        return adescr;
    }

    public void setAdescr(String adescr) {
        this.adescr = adescr;
    }

    public LocalDate getAdate() {
        return adate;
    }

    public void setAdate(LocalDate adate) {
        this.adate = adate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.aid;
        hash = 17 * hash + Objects.hashCode(this.course);
        hash = 17 * hash + Objects.hashCode(this.atitle);
        hash = 17 * hash + Objects.hashCode(this.adescr);
        hash = 17 * hash + Objects.hashCode(this.adate);
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
        final Assignment other = (Assignment) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (!Objects.equals(this.atitle, other.atitle)) {
            return false;
        }
        if (!Objects.equals(this.adescr, other.adescr)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.adate, other.adate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assignment{" + "aid=" + aid + ", course=" + course + ", atitle=" + atitle + ", adescr=" + adescr + ", adate=" + adate + '}';
    }
    
    
}

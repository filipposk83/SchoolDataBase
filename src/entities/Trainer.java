package entities;

import java.util.Objects;



public class Trainer {
    private int tid;
    private String tfname;
    private String tlname;
    private Course course;
    private String tsubject;

    public Trainer() {
    }

    public Trainer(String tfname, String tlname, Course course, String tsubject) {
        this.tfname = tfname;
        this.tlname = tlname;
        this.course = course;
        this.tsubject = tsubject;
    }

    
    public Trainer(int tid, String tfname, String tlname, Course course, String tsubject) {
        this.tid = tid;
        this.tfname = tfname;
        this.tlname = tlname;
        this.course = course;
        this.tsubject = tsubject;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTfname() {
        return tfname;
    }

    public void setTfname(String tfname) {
        this.tfname = tfname;
    }

    public String getTlname() {
        return tlname;
    }

    public void setTlname(String tlname) {
        this.tlname = tlname;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTsubject() {
        return tsubject;
    }

    public void setTsubject(String tsubject) {
        this.tsubject = tsubject;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.tid;
        hash = 67 * hash + Objects.hashCode(this.tfname);
        hash = 67 * hash + Objects.hashCode(this.tlname);
        hash = 67 * hash + Objects.hashCode(this.course);
        hash = 67 * hash + Objects.hashCode(this.tsubject);
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
        final Trainer other = (Trainer) obj;
        if (this.tid != other.tid) {
            return false;
        }
        if (!Objects.equals(this.tfname, other.tfname)) {
            return false;
        }
        if (!Objects.equals(this.tlname, other.tlname)) {
            return false;
        }
        if (!Objects.equals(this.tsubject, other.tsubject)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trainer{" + "tid=" + tid + ", tfname=" + tfname + ", tlname=" + tlname + ", course=" + course + ", tsubject=" + tsubject + '}';
    }
    
    

    
}

package entities;

import java.util.Objects;

public class Classroom {
    private int clid;
    private Course course;
    private Student student;

    public Classroom() {
    }

    public Classroom(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    
    public Classroom(int clid, Course course, Student student) {
        this.clid = clid;
        this.course = course;
        this.student = student;
    }

    public int getClid() {
        return clid;
    }

    public void setClid(int clid) {
        this.clid = clid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.clid;
        hash = 79 * hash + Objects.hashCode(this.course);
        hash = 79 * hash + Objects.hashCode(this.student);
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
        final Classroom other = (Classroom) obj;
        if (this.clid != other.clid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classroom{" + "clid=" + clid + ", course=" + course + ", student=" + student + '}';
    }
    
    
}

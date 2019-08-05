package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    private int cid;
    private String ctitle;
    private String cstream;
    private String ctype;
    private LocalDate cstart;
    private LocalDate cend;

    public Course() {
    }

    public Course(String ctitle, String cstream, String ctype, LocalDate cstart, LocalDate cend) {
        this.ctitle = ctitle;
        this.cstream = cstream;
        this.ctype = ctype;
        this.cstart = cstart;
        this.cend = cend;
    }

    
    public Course(int cid, String ctitle, String cstream, String ctype, LocalDate cstart, LocalDate cend) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cstream = cstream;
        this.ctype = ctype;
        this.cstart = cstart;
        this.cend = cend;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCstream() {
        return cstream;
    }

    public void setCstream(String cstream) {
        this.cstream = cstream;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public LocalDate getCstart() {
        return cstart;
    }

    public void setCstart(LocalDate cstart) {
        this.cstart = cstart;
    }

    public LocalDate getCend() {
        return cend;
    }

    public void setCend(LocalDate cend) {
        this.cend = cend;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.cid;
        hash = 47 * hash + Objects.hashCode(this.ctitle);
        hash = 47 * hash + Objects.hashCode(this.cstream);
        hash = 47 * hash + Objects.hashCode(this.ctype);
        hash = 47 * hash + Objects.hashCode(this.cstart);
        hash = 47 * hash + Objects.hashCode(this.cend);
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
        final Course other = (Course) obj;
        if (this.cid != other.cid) {
            return false;
        }
        if (!Objects.equals(this.ctitle, other.ctitle)) {
            return false;
        }
        if (!Objects.equals(this.cstream, other.cstream)) {
            return false;
        }
        if (!Objects.equals(this.ctype, other.ctype)) {
            return false;
        }
        if (!Objects.equals(this.cstart, other.cstart)) {
            return false;
        }
        if (!Objects.equals(this.cend, other.cend)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "cid=" + cid + ", ctitle=" + ctitle + ", cstream=" + cstream + ", ctype=" + ctype + ", cstart=" + cstart + ", cend=" + cend + '}';
    }
    
    
}

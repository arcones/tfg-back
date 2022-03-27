package es.upm.tfgback.model;

import javax.persistence.*;
@Entity
@Table(name = "tfg")
public class TFG {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "student_id")
    private long studentId;

    public TFG() {
    }
    
    public TFG(long studentId, String title) {
        this.title = title;
        this.studentId = studentId;
    }
    
    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "TFG{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
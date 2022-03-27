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

    @Column(name = "director_id")
    private long directorId;

    @Column(name = "status")
    private String status;

    public TFG() {
    }

    public TFG(String title, long studentId, long directorId, String status) {
        this.title = title;
        this.studentId = studentId;
        this.directorId = directorId;
        this.status = status;
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

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
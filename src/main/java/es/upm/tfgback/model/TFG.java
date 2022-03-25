package es.upm.tfgback.model;

import javax.persistence.*;
@Entity
@Table(name = "TFG")
public class TFG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;
    public TFG() {
    }
    public TFG(String title) {
        this.title = title;
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
    @Override
    public String toString() {
        return "TFG{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
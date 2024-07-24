package pbo.f01.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Students")

public class Student {
    public static final int length = 0;
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "entrance")
    private int entrance;
    @Column(name = "gender")
    private String gender;

    @OneToOne(targetEntity = Dorm.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "student_dorm", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dorm_name", referencedColumnName = "name"))
    private Set<Dorm> dorms;

    public Student() {
        // empty
    }

    public Student(String id, String name, String entrance, String gender) {
        this.id = id;
        this.name = name;
        this.entrance = 0;
        this.gender = gender;
    }

    public Student(String id, String name, String entrance, String gender, Set<Dorm> dorms) {
        this.id = id;
        this.name = name;
        this.entrance = 0;
        this.gender = gender;
        this.dorms = dorms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Dorm> getDorms() {
        return dorms;
    }

    public void setDorms(Set<Dorm> dorms) {
        this.dorms = dorms;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + entrance + "|" + gender;

    }

}

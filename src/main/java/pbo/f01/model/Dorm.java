package pbo.f01.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dorm")

public class Dorm {

    @Id
    @Column(name = "name", length = 225, nullable = false)
    private String name;
    @Column(name = "capacity", nullable = false)
    private int capacity;
    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students;

    public Dorm() {
        // empty
    }

    public Dorm(String name, String capacity, String gender, Set<Student> students) {
        this.name = name;
        this.capacity = 0;
        this.gender = gender;
        this.students = students;
    }

    public Dorm(String name, String capacity, String gender) {
        this.name = name;
        this.capacity = 0;
        this.gender = gender;
    }

    public Dorm(String string, String string2, String string3, String string4, String string5) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return name + "|" + capacity + "|" + gender;

    }

}

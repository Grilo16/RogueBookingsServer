package RogueBookings.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @JsonBackReference(value = "lessons_students")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Student> students = new LinkedHashSet<>();

    @JsonBackReference(value = "lessons_teachers")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new LinkedHashSet<>();

    public Lesson() {
    }

    public Lesson(String name, String date, Integer capacity, String description, Long price, Business business, Set<Student> students, Set<Teacher> teachers) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.description = description;
        this.price = price;
        this.business = business;
        this.students = students;
        this.teachers = teachers;
    }

    public Lesson(Long id, String name, String date, Integer capacity, String description, Long price, Business business, Set<Student> students, Set<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.description = description;
        this.price = price;
        this.business = business;
        this.students = students;
        this.teachers = teachers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lesson lesson = (Lesson) o;
        return id != null && Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
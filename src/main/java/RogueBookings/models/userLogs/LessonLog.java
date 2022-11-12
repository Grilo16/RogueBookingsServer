package RogueBookings.models.userLogs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "lesson_logs")
public class LessonLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_logs_gen")
    @SequenceGenerator(name = "lesson_logs_gen", sequenceName = "lesson_logs_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lesson_name", nullable = false)
    private String lessonName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "action", nullable = false)
    private String action;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date = new java.util.Date();

    @JsonBackReference(value = "user_logs")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_logs_id")
    private UserLogs userLogs;

    public LessonLog() {
    }

    public LessonLog(String lessonName, String role, String action, UserLogs userLogs) {
        this.lessonName = lessonName;
        this.role = role;
        this.action = action;
        this.userLogs = userLogs;
    }

    public LessonLog(Long id, String lessonName, String role, String action, UserLogs userLogs) {
        this.id = id;
        this.lessonName = lessonName;
        this.role = role;
        this.action = action;
        this.userLogs = userLogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserLogs getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(UserLogs userLogs) {
        this.userLogs = userLogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LessonLog that = (LessonLog) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

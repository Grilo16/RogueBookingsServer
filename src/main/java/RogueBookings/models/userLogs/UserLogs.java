package RogueBookings.models.userLogs;

import RogueBookings.models.User;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_logs")
public class UserLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_logs_seq")
    @SequenceGenerator(name = "user_logs_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userLogs", cascade = CascadeType.ALL)
    private Set<LessonLog> lessonLogs = new LinkedHashSet<>();


    @Temporal(TemporalType.DATE)
    @Column(name = "sign_up_date", nullable = false)
    private Date signUpDate = new java.util.Date();
    public UserLogs() {
    }

    public UserLogs(User user, Set<LessonLog> lessonLogs) {
        this.user = user;
        this.lessonLogs = lessonLogs;
    }

    public UserLogs(Long id, User user, Set<LessonLog> lessonLogs) {
        this.id = id;
        this.user = user;
        this.lessonLogs = lessonLogs;
    }

    public Set<LessonLog> getLessonLogs() {
        return lessonLogs;
    }

    public void setLessonLogs(Set<LessonLog> lessonLogs) {
        this.lessonLogs = lessonLogs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserLogs userLogs = (UserLogs) o;
        return id != null && Objects.equals(id, userLogs.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
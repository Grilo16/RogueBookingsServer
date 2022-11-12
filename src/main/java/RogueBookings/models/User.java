package RogueBookings.models;

import RogueBookings.models.userLogs.UserLogs;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, length = 319)
    private String email;

    @Column(name = "credits", nullable = false)
    private Long credits = 0L;

    @JsonBackReference(value = "businesses_owners")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Owner> businesses = new LinkedHashSet<>();


    @JsonBackReference(value = "businesses_members")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Member> memberships = new LinkedHashSet<>();


    @JsonBackReference("user_logs")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private UserLogs userLogs;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String userLayout;


    public User() {
    }

    public User(String firstName, String lastName, String email, Long credits, Set<Owner> businesses, Set<Member> memberships, UserLogs userLogs, String userLayout) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.credits = credits;
        this.businesses = businesses;
        this.memberships = memberships;
        this.userLogs = userLogs;
        this.userLayout = userLayout;
    }

    public User(Long id, String firstName, String lastName, String email, Long credits, Set<Owner> businesses, Set<Member> memberships, UserLogs userLogs, String userLayout) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.credits = credits;
        this.businesses = businesses;
        this.memberships = memberships;
        this.userLogs = userLogs;
        this.userLayout = userLayout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
    }

    public Set<Owner> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Owner> businesses) {
        this.businesses = businesses;
    }

    public Set<Member> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Member> memberships) {
        this.memberships = memberships;
    }

    public UserLogs getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(UserLogs userLogs) {
        this.userLogs = userLogs;
    }

    public String getUserLayout() {
        return userLayout;
    }

    public void setUserLayout(String userLayout) {
        this.userLayout = userLayout;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
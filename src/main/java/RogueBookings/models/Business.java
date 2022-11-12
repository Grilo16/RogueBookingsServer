package RogueBookings.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_seq")
    @SequenceGenerator(name = "business_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "balance", nullable = false)
    private Long balance = 0L;

    @JsonBackReference(value = "businesses_lessons")
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Lesson> lessons = new LinkedHashSet<>();

    @JsonBackReference(value = "businesses_owners")
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Owner> owners = new LinkedHashSet<>();

    @JsonBackReference(value = "businesses_members")
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Member> members = new LinkedHashSet<>();

    public Business() {
    }

    public Business(String name, Long balance, Set<Lesson> lessons, Set<Owner> owners, Set<Member> members) {
        this.name = name;
        this.balance = balance;
        this.lessons = lessons;
        this.owners = owners;
        this.members = members;
    }

    public Business(Long id, String name, Long balance, Set<Lesson> lessons, Set<Owner> owners, Set<Member> members) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.lessons = lessons;
        this.owners = owners;
        this.members = members;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
package RogueBookings.dataTransferObjects;

import RogueBookings.models.Lesson;
import RogueBookings.models.Owner;
import RogueBookings.models.Member;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BusinessDTO {

    private Long id;
    private String name;

    private Long balance;

    private Set<Member> members;

    private Set<Owner> owners;

    private Set<Lesson> lessons;

    public BusinessDTO() {
    }

    public BusinessDTO(Long id, String name, Long balance, Set<Member> members, Set<Owner> owners, Set<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.members = members;
        this.owners = owners;
        this.lessons = lessons;
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

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}

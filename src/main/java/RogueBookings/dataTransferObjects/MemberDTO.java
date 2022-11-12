package RogueBookings.dataTransferObjects;

import RogueBookings.models.Business;
import RogueBookings.models.User;
import org.springframework.stereotype.Component;

@Component
public class MemberDTO {

    private Long id;
    private Business business;
    private User user;

    public MemberDTO() {
    }

    public MemberDTO(Long id, Business business, User user) {
        this.id = id;
        this.business = business;
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
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
}

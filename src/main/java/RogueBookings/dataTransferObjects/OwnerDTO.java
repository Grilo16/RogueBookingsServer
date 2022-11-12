package RogueBookings.dataTransferObjects;

import RogueBookings.models.Business;
import RogueBookings.models.User;
import org.springframework.stereotype.Component;

@Component
public class OwnerDTO {

    private Business business;

    private User owner;

    public OwnerDTO() {
    }

    public OwnerDTO(Business business, User owner) {
        this.business = business;
        this.owner = owner;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

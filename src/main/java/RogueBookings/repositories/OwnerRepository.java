package RogueBookings.repositories;

import RogueBookings.models.Business;
import RogueBookings.models.Owner;
import RogueBookings.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByBusinessAndOwner(Business business, User owner);

}
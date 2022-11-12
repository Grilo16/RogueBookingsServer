package RogueBookings.repositories;

import RogueBookings.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    boolean existsByNameIgnoreCase(String name);


    List<Business> findByOwners_Owner_Id(Long id);



}
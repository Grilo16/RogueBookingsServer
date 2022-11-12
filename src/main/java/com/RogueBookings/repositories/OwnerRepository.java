package com.RogueBookings.repositories;

import com.RogueBookings.models.Business;
import com.RogueBookings.models.Owner;
import com.RogueBookings.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByBusinessAndOwner(Business business, User owner);

}
package com.RogueBookings.repositories;

import com.RogueBookings.models.Member;
import com.RogueBookings.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @NonNull
    User findUserByEmail(String email);




}
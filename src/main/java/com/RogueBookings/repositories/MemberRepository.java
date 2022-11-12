package com.RogueBookings.repositories;

import com.RogueBookings.models.Business;
import com.RogueBookings.models.Member;
import com.RogueBookings.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByBusinessAndUser(Business business, User user);

    List<Member> findMembershipsByUser_Id(Long id);


}
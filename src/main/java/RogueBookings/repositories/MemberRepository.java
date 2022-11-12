package RogueBookings.repositories;

import RogueBookings.models.Business;
import RogueBookings.models.Member;
import RogueBookings.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByBusinessAndUser(Business business, User user);

    List<Member> findMembershipsByUser_Id(Long id);


}
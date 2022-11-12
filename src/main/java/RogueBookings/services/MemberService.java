package RogueBookings.services;

import RogueBookings.dataTransferObjects.MemberDTO;
import RogueBookings.models.Business;
import RogueBookings.models.Member;
import RogueBookings.models.User;
import RogueBookings.oopsies.OopsieRequestException;
import RogueBookings.repositories.BusinessRepository;
import RogueBookings.repositories.MemberRepository;
import RogueBookings.repositories.UserRepository;
import RogueBookings.utilities.DTOConverter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private UserRepository userRepository;
    private BusinessRepository businessRepository;

    private DTOConverter<MemberDTO, Member> dtoConverter;
    private Type memberDTOType = new TypeToken<MemberDTO>() {}.getType();
    private Type memberType = new TypeToken<Member>() {}.getType();



    @Autowired
    public MemberService(MemberRepository memberRepository, UserRepository userRepository, BusinessRepository businessRepository, DTOConverter<MemberDTO, Member> dtoConverter) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.dtoConverter = dtoConverter;
    }


    public List<Member> getAllBusinessMembers() {
        return memberRepository.findAll();
    }

    public MemberDTO addMemberToBusiness(Long userId, Long businessId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new OopsieRequestException("User no exist, try some other id");
        }
        Optional<Business> business = businessRepository.findById(businessId);
        if (business.isEmpty()) {
            throw new OopsieRequestException("Cant become a member of a non existant business can we?");
        }
        if (memberRepository.existsByBusinessAndUser(business.get(), user.get())){
            throw new OopsieRequestException("Already a member here, thanks for the support =))");
        }
        Member member = new Member();
        member.setUser(user.get());
        member.setBusiness(business.get());
        try {
            return dtoConverter.entityToDTO(memberRepository.save(member), memberDTOType);
        }catch (Exception e){
            throw new OopsieRequestException("nope");
        }
    }

    public ResponseEntity<String> removeMemberFromBusiness(Long memberId) {
        if (!memberRepository.existsById(memberId)){
            throw new OopsieRequestException("What does not exist shall not be erased");
        }
        try{
            memberRepository.deleteById(memberId);
            return new ResponseEntity<>("Successfully deleted member", HttpStatus.OK);
        }catch (Exception e){
            throw new OopsieRequestException("Sorry deletion failed for some reason");
        }
    }

    public List<MemberDTO> getAllMembersByUserId(Long userId) {
        return dtoConverter.entityToDTO(memberRepository.findMembershipsByUser_Id(userId), memberDTOType);
    }
}

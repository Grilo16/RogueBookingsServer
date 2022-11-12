package RogueBookings.controllers;


import RogueBookings.dataTransferObjects.BusinessDTO;
import RogueBookings.dataTransferObjects.MemberDTO;
import RogueBookings.models.Member;
import RogueBookings.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(path = "members")
public class MemberController {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllBusinessMembers(){

        return memberService.getAllBusinessMembers();
    }

    @GetMapping(value = "/user/{userId}")
    public List<MemberDTO> getAllMembersByUserId(@PathVariable Long userId){
        return memberService.getAllMembersByUserId(userId);
    }


    @PostMapping(path = "/{userId}/business/{businessId}")
    public MemberDTO addMemberToBusiness(@PathVariable Long userId, @PathVariable Long businessId){
        return memberService.addMemberToBusiness(userId, businessId);
    }

    @PutMapping(path = "/{memberId}/leave")
    public ResponseEntity<String> removeMemberFromBusiness(@PathVariable Long memberId){
        return memberService.removeMemberFromBusiness(memberId);
    }
}

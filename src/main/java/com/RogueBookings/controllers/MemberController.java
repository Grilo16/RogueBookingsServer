package com.RogueBookings.controllers;


import com.RogueBookings.dataTransferObjects.MemberDTO;
import com.RogueBookings.models.Member;
import com.RogueBookings.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "https://grilo16.github.io/")
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

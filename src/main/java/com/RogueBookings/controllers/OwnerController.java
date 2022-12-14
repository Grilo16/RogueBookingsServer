package com.RogueBookings.controllers;


import com.RogueBookings.dataTransferObjects.OwnerDTO;
import com.RogueBookings.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "https://grilo16.github.io/")
@RequestMapping(path = "owners")
public class OwnerController {

    OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDTO> getAllBusinessOwners(){
        return ownerService.getAllBusinessOwners();
    }

    @PostMapping(value = "/{userId}/business/{businessId}")
    public OwnerDTO addOwnerToBusiness(@PathVariable Long userId, @PathVariable Long businessId){
        return ownerService.addNewOwnerToBusiness(userId, businessId);
    }

    @PutMapping(path = "/{ownerId}/leave")
    public ResponseEntity<String> removeOwnerFromBusiness(@PathVariable Long ownerId){
        return ownerService.removeOwnerFromBusiness(ownerId);
    }
}

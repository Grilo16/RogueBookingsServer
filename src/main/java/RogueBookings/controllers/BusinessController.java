package RogueBookings.controllers;

import RogueBookings.dataTransferObjects.BusinessDTO;
import RogueBookings.models.Business;
import RogueBookings.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(path = "businesses")
public class BusinessController {

    BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }


    @GetMapping
    public List<BusinessDTO> getAllBusinesses(){
        return businessService.getAllBusinesses();
    }

    @GetMapping(value = "/{businessId}")
    public BusinessDTO getBusinessByBusinessId(@PathVariable Long businessId){
        return businessService.getBusinessById(businessId);
    }

    @GetMapping(value = "/user/{userId}")
    public List<BusinessDTO> getAllBusinessesByUserId(@PathVariable Long userId){
        return businessService.getAllBusinessesByUserId(userId);
    }
    
    @PostMapping(value = "/owner/{userId}")
    public BusinessDTO createNewBusiness(@RequestBody BusinessDTO businessDTO, @PathVariable Long userId){
        return businessService.createNewBusinessByUserId(businessDTO, userId);
    }

    @DeleteMapping(value = "/{businessId}")
    public ResponseEntity<String> deleteBusinessById(@PathVariable Long businessId){
        return businessService.deleteBusinessByid(businessId);
    }

    @PatchMapping(path = "/{businessId}")
    public BusinessDTO editBusiness(@RequestBody BusinessDTO businessDTO, @PathVariable Long businessId){
        return businessService.editBusiness(businessDTO, businessId);
    }
}

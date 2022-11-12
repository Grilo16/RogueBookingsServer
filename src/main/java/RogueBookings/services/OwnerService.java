package RogueBookings.services;


import RogueBookings.oopsies.OopsieRequestException;
import RogueBookings.utilities.DTOConverter;
import RogueBookings.dataTransferObjects.OwnerDTO;
import RogueBookings.models.Business;
import RogueBookings.models.Owner;
import RogueBookings.models.User;
import RogueBookings.repositories.BusinessRepository;
import RogueBookings.repositories.OwnerRepository;
import RogueBookings.repositories.UserRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;
    private UserRepository userRepository;
    private BusinessRepository businessRepository;

    DTOConverter<OwnerDTO, Owner> dtoConverter;
    Type ownerDTOType = new TypeToken<OwnerDTO>() {}.getType();
    Type ownerType = new TypeToken<Owner>() {}.getType();

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, UserRepository userRepository, BusinessRepository businessRepository, DTOConverter<OwnerDTO, Owner> dtoConverter) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.dtoConverter = dtoConverter;
    }

    public List<OwnerDTO> getAllBusinessOwners() {
        return dtoConverter.entityToDTO(ownerRepository.findAll(), ownerDTOType);
    }

    public OwnerDTO addNewOwnerToBusiness(Long userId, Long businessId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new OopsieRequestException("User no exist, try some other id");
        }
        Optional<Business> business = businessRepository.findById(businessId);
        if (business.isEmpty()) {
            throw new OopsieRequestException("Cant become an owner of a non existant business can we?");
        }
        if (ownerRepository.existsByBusinessAndOwner(business.get(), user.get())){
            throw new OopsieRequestException("Stop being greedy, you already have this business");
        }
        Owner owner = new Owner();
        owner.setBusiness(business.get());
        owner.setOwner(user.get());
        try {
            return dtoConverter.entityToDTO(ownerRepository.save(owner), ownerDTOType);
        }catch (Exception e){
            throw new OopsieRequestException("An owner yet you are not, try again and you might become");
        }
    }

    public ResponseEntity<String> removeOwnerFromBusiness(Long ownerId) {
        if (!ownerRepository.existsById(ownerId)){
            throw new OopsieRequestException("What does not exist shall not be erased");
        }
        try{
            ownerRepository.deleteById(ownerId);
            return new ResponseEntity<>("Successfully deleted owner", HttpStatus.OK);
        }catch (Exception e){
            throw new OopsieRequestException("Sorry deletion failed for some reason");
        }
    }
}

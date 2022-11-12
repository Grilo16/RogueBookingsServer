package RogueBookings.services;

import RogueBookings.dataTransferObjects.UserDTO;
import RogueBookings.models.Owner;
import RogueBookings.models.User;
import RogueBookings.repositories.UserRepository;
import RogueBookings.utilities.DTOConverter;
import RogueBookings.dataTransferObjects.BusinessDTO;
import RogueBookings.oopsies.OopsieRequestException;
import RogueBookings.models.Business;
import RogueBookings.repositories.BusinessRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    ModelMapper modelMapper;

    BusinessRepository businessRepository;

    private UserRepository userRepository;
    DTOConverter<BusinessDTO, Business> dtoConverter;

    Type businessDTOType = new TypeToken<BusinessDTO>() {}.getType();

    Type businessType = new TypeToken<Business>() {}.getType();





    @Autowired
    public BusinessService(BusinessRepository businessRepository, UserRepository userRepository, DTOConverter<BusinessDTO, Business> dtoConverter) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public List<BusinessDTO> getAllBusinesses() {
        return dtoConverter.entityToDTO(businessRepository.findAll(), businessDTOType);
    }

    public BusinessDTO getBusinessById(Long businessId){
        Optional<Business> business = businessRepository.findById(businessId);
        BusinessDTO businessDTO;
        if (business.isEmpty()){
            throw new OopsieRequestException("business doesnt exist soz");
        }
        try {
            businessDTO = dtoConverter.entityToDTO(business.get(), businessDTOType);
        } catch (Exception e){
            throw new OopsieRequestException("ahh that didnt work no know why");
        }
        return businessDTO;
    }



    public BusinessDTO createNewBusinessByUserId(BusinessDTO businessDTO, Long userId) {
        Business business = dtoConverter.DTOtoEntity(businessDTO, businessType);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new OopsieRequestException("loool user donsnt exist, do try again tho");
        } else if (businessRepository.existsByNameIgnoreCase(business.getName())) {
            throw new OopsieRequestException("oooohh nono copycat, another business already has this name");
        }
        Owner owner = new Owner();
        owner.setBusiness(business);
        owner.setOwner(user.get());
        business.getOwners().add(owner);
        try{
            return dtoConverter.entityToDTO(businessRepository.save(business), businessDTOType);
        }catch (Exception e){
            throw new OopsieRequestException("I dont think you sent the correct data to create a business");
        }
    }

    public ResponseEntity<String> deleteBusinessByid(Long businessid) {
        if (!businessRepository.existsById(businessid)){
            throw new OopsieRequestException("Oopsie This business doesnt exist sadge");
        }
        try {
            businessRepository.deleteById(businessid);
            return new ResponseEntity<>("Successfully deleted business", HttpStatus.OK);
        } catch (Exception e){
            throw new OopsieRequestException("deletion failed, so did my creativity for funny delete messages");
        }
    }

    public BusinessDTO editBusiness(BusinessDTO businessDTO, Long businessId) {
        Optional<Business> business = businessRepository.findById(businessId);
        if (business.isPresent()){
            Business businessToUpdate = business.get();
            Business updatedBusiness;
            try {
                Business patchObject = dtoConverter.DTOtoEntity(businessDTO, businessType);
                modelMapper.map(patchObject, businessToUpdate);
                updatedBusiness = businessRepository.save(businessToUpdate);
            }catch (Exception e){
                throw new OopsieRequestException("no good nope, try editing something else");
            }
            return dtoConverter.entityToDTO(updatedBusiness, businessDTOType);
        }
        throw new OopsieRequestException("Oopsie This lesson doesnt exist sadge");
    }

    public List<BusinessDTO> getAllBusinessesByUserId(Long userId) {
        return dtoConverter.entityToDTO(businessRepository.findByOwners_Owner_Id(userId), businessDTOType);
    }
}

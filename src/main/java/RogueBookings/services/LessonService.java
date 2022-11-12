package RogueBookings.services;

import RogueBookings.utilities.DTOConverter;
import RogueBookings.dataTransferObjects.LessonDTO;
import RogueBookings.oopsies.OopsieRequestException;
import RogueBookings.models.Business;
import RogueBookings.models.Lesson;
import RogueBookings.repositories.BusinessRepository;
import RogueBookings.repositories.LessonRepository;
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
public class LessonService {

    LessonRepository lessonRepository;
    BusinessRepository businessRepository;
    ModelMapper modelMapper;
    DTOConverter<LessonDTO, Lesson> dtoConverter;
    Type lessonDTOType = new TypeToken<LessonDTO>() {}.getType();
    Type lessonType = new TypeToken<Lesson>() {}.getType();

    @Autowired
    public LessonService(LessonRepository lessonRepository, BusinessRepository businessRepository, DTOConverter<LessonDTO, Lesson> dtoConverter) {
        this.lessonRepository = lessonRepository;
        this.businessRepository = businessRepository;
        this.dtoConverter = dtoConverter;
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public List<LessonDTO> getAllLessons(){
        return dtoConverter.entityToDTO(lessonRepository.findAll(), lessonDTOType);
    }

    public LessonDTO createNewLessonBusinessId(Long businessId, LessonDTO lessonDTO) {
        Lesson lesson = dtoConverter.DTOtoEntity(lessonDTO, lessonType);
        Optional<Business> business = businessRepository.findById(businessId);
        if (business.isEmpty()){
            throw new OopsieRequestException("loool business donsnt exist, do try again tho");
        }
        Business selectedBusiness = business.get();
        lesson.setBusiness(selectedBusiness);
        try{
            return dtoConverter.entityToDTO(lessonRepository.save(lesson), lessonDTOType);
        }catch (Exception e){
            throw new OopsieRequestException("I dont think you sent the correct data to create a lesson");
        }
    }

    public ResponseEntity<String> deleteLessonById(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)){
            throw new OopsieRequestException("Oopsie This lesson doesnt exist sadge");
        }
        try {
            lessonRepository.deleteById(lessonId);
            return new ResponseEntity<>("Successfully deleted lesson", HttpStatus.OK);
        } catch (Exception e){
            throw new OopsieRequestException("Deletion what lesson? it didnt work try again.");
        }

    }

    public LessonDTO editLesson(LessonDTO lessonDTO, Long lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isPresent()){
            Lesson lessonToUpdate = lesson.get();
            Lesson updatedLesson;
                try {
                    Lesson patchObject = dtoConverter.DTOtoEntity(lessonDTO, lessonType);
                    modelMapper.map(patchObject, lessonToUpdate);
                    updatedLesson = lessonRepository.save(lessonToUpdate);
                }catch (Exception e){
                    throw new OopsieRequestException("no good nope, try editing something else");
                }
            return dtoConverter.entityToDTO(updatedLesson, lessonDTOType);
        }
        throw new OopsieRequestException("Oopsie This lesson doesnt exist sadge");
    }
}



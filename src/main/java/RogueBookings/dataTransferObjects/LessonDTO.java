package RogueBookings.dataTransferObjects;

import RogueBookings.utilities.DTOConverter;
import RogueBookings.models.Business;
import RogueBookings.models.Teacher;
import RogueBookings.models.User;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;


@Component
public class LessonDTO {





    private String name;

    private String date;

    private Integer capacity;

    private String description;

    private Long price;



    DTOConverter<BusinessDTO, Business> businessDTOConverter = new DTOConverter<>();

    Type businessDTOType = new TypeToken<BusinessDTO>() {}.getType();

    DTOConverter<TeacherDTO, Teacher> teacherDTOConverter = new DTOConverter<>();

    Type teacherDTOType = new TypeToken<TeacherDTO>() {}.getType();


    DTOConverter<StudentDTO, User> studentDTOConverter = new DTOConverter<>();

    Type studentDTOType = new TypeToken<StudentDTO>() {}.getType();


    public LessonDTO() {
    }

    public LessonDTO(String name, String date, Integer capacity, String description, Long price) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

package RogueBookings.dataTransferObjects;

import org.springframework.stereotype.Component;

@Component
public class StudentDTO {

    String studentName;

    public StudentDTO() {
    }

    public StudentDTO(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

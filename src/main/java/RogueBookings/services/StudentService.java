package RogueBookings.services;

import RogueBookings.dataTransferObjects.StudentDTO;
import RogueBookings.models.*;
import RogueBookings.models.userLogs.LessonLog;
import RogueBookings.oopsies.OopsieRequestException;
import RogueBookings.repositories.*;
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
public class StudentService {

    private LessonRepository lessonRepository;
    private LessonLogRepository lessonLogRepository;

    private StudentRepository studentRepository;

    private MemberRepository memberRepository;

    private DTOConverter<StudentDTO, Student> dtoConverter;
    private Type studentDTOType = new TypeToken<StudentDTO>() {}.getType();
    private Type studentType = new TypeToken<Student>() {}.getType();



    @Autowired
    public StudentService(LessonRepository lessonRepository, LessonLogRepository lessonLogRepository, StudentRepository studentRepository, MemberRepository memberRepository, DTOConverter<StudentDTO, Student> dtoConverter) {
        this.lessonRepository = lessonRepository;
        this.lessonLogRepository = lessonLogRepository;
        this.studentRepository = studentRepository;
        this.memberRepository = memberRepository;
        this.dtoConverter = dtoConverter;
    }

    public List<Student> getAllLessonStudents() {
        return studentRepository.findAll();
    }

    public StudentDTO addStudentToLesson(Long memberId, Long lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isEmpty()) {
            throw new OopsieRequestException("Lesson no exist, try some other id");
        }
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new OopsieRequestException("Cant study in a lesson on a business u aint a member of");
        }
        if (studentRepository.existsByMemberAndLesson(member.get(), lesson.get())){
            throw new OopsieRequestException("You already study here, go get some rest =) ");
        }

        LessonLog lessonLog = new LessonLog();
        lessonLog.setLessonName(lesson.get().getName());
        lessonLog.setRole("student");
        lessonLog.setAction("Signed up");
        lessonLog.setUserLogs(member.get().getUser().getUserLogs());
        member.get().getUser().getUserLogs().getLessonLogs().add(lessonLog);
        Student student = new Student();
        student.setMember(member.get());
        student.setLesson(lesson.get());
        try {
            return dtoConverter.entityToDTO(studentRepository.save(student), studentDTOType);
        }catch (Exception e){
            throw new OopsieRequestException("A student yet you are not, try again and you might become");
        }
    }

    public ResponseEntity<String> removeStudentFromLessonByStudentId(Long studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()){
            throw new OopsieRequestException("What does not exist shall not be erased");
        }
        Member member = student.get().getMember();
        LessonLog lessonLog = new LessonLog();
        lessonLog.setLessonName(student.get().getLesson().getName());
        lessonLog.setRole("student");
        lessonLog.setAction("Left teaching job found a new purpoise in life");
        lessonLog.setUserLogs(member.getUser().getUserLogs());
        member.getUser().getUserLogs().getLessonLogs().add(lessonLog);
        try{
            studentRepository.deleteById(studentId);
        }catch (Exception e){
            throw new OopsieRequestException("Sorry deletion failed for some reason");
        }
        memberRepository.save(member);
        return new ResponseEntity<>("Successfully found a new purpoise in life and left lesson", HttpStatus.OK);





    }
}


package RogueBookings.services;

import RogueBookings.dataTransferObjects.TeacherDTO;
import RogueBookings.oopsies.OopsieRequestException;
import RogueBookings.models.*;
import RogueBookings.models.userLogs.LessonLog;
import RogueBookings.repositories.LessonLogRepository;
import RogueBookings.repositories.LessonRepository;
import RogueBookings.repositories.MemberRepository;
import RogueBookings.repositories.TeacherRepository;
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
public class TeacherService {


    private MemberRepository memberRepository;
    private TeacherRepository teacherRepository;
    private LessonRepository lessonRepository;

    private LessonLogRepository lessonLogRepository;
    private DTOConverter<TeacherDTO, Teacher> dtoConverter;
    private Type teacherDTOType = new TypeToken<TeacherDTO>() {}.getType();
    private Type teacherType = new TypeToken<Teacher>() {}.getType();


    @Autowired
    public TeacherService(MemberRepository memberRepository, TeacherRepository teacherRepository, LessonRepository lessonRepository, LessonLogRepository lessonLogRepository, DTOConverter<TeacherDTO, Teacher> dtoConverter) {
        this.memberRepository = memberRepository;
        this.teacherRepository = teacherRepository;
        this.lessonRepository = lessonRepository;
        this.lessonLogRepository = lessonLogRepository;
        this.dtoConverter = dtoConverter;
    }


    public List<Teacher> getAllLessonTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherDTO addTeacherToLesson(Teacher teacher, Long memberId, Long lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isEmpty()) {
            throw new OopsieRequestException("Lesson no exist, try some other id");
        }
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new OopsieRequestException("Cant teach a lesson on a business u aint a member of");
        }
        if (teacherRepository.existsByLessonAndMember(lesson.get(), member.get())){
            throw new OopsieRequestException("You already teach here, go get some rest =) ");
        }

        LessonLog lessonLog = new LessonLog();
        lessonLog.setLessonName(lesson.get().getName());
        lessonLog.setRole("teacher");
        lessonLog.setAction("Signed up");
        lessonLog.setUserLogs(member.get().getUser().getUserLogs());
        member.get().getUser().getUserLogs().getLessonLogs().add(lessonLog);

        teacher.setMember(member.get());
        teacher.setLesson(lesson.get());
        try {
            return dtoConverter.entityToDTO(teacherRepository.save(teacher), teacherDTOType);
        }catch (Exception e){
            throw new OopsieRequestException("An owner yet you are not, try again and you might become");
        }
    }

    public ResponseEntity<String> removeTeacherFromLessonByTeacherId(Long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()){
            throw new OopsieRequestException("What does not exist shall not be erased");
        }
        Member member = teacher.get().getMember();
        LessonLog lessonLog = new LessonLog();
        lessonLog.setLessonName(teacher.get().getLesson().getName());
        lessonLog.setRole("teacher");
        lessonLog.setAction("Left teaching job found a new purpoise in life");
        lessonLog.setUserLogs(member.getUser().getUserLogs());
        member.getUser().getUserLogs().getLessonLogs().add(lessonLog);
        try{
            teacherRepository.deleteById(teacherId);
        }catch (Exception e){
            throw new OopsieRequestException("Sorry deletion failed for some reason");
        }
        memberRepository.save(member);
        return new ResponseEntity<>("Successfully found a new purpoise in life and left lesson", HttpStatus.OK);







    }
}

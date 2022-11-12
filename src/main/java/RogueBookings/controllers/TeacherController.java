package RogueBookings.controllers;

import RogueBookings.dataTransferObjects.TeacherDTO;
import RogueBookings.models.Teacher;
import RogueBookings.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(path = "teachers")
public class TeacherController {

    TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllLessonTeachers(){
        return teacherService.getAllLessonTeachers();
    }

    @PostMapping(path = "/{memberId}/lesson/{lessonId}")
    public TeacherDTO addTeacherToLesson(@RequestBody Teacher teacher, @PathVariable Long memberId, @PathVariable Long lessonId){
        return teacherService.addTeacherToLesson(teacher, memberId, lessonId);
    }

    @PutMapping(path = "/{teacherId}/leave")
    public ResponseEntity<String> removeTeacherFromLesson(@PathVariable Long teacherId){
        return teacherService.removeTeacherFromLessonByTeacherId(teacherId);
    }
}

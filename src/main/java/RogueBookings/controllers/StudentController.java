package RogueBookings.controllers;


import RogueBookings.dataTransferObjects.StudentDTO;
import RogueBookings.services.StudentService;
import RogueBookings.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(path = "students")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllLessonStudents(){
        return studentService.getAllLessonStudents();
    }

    @PostMapping(value = "/{memberId}/lesson/{lessonId}")
    public StudentDTO addStudentToLesson(@PathVariable Long memberId, @PathVariable Long lessonId){
        return studentService.addStudentToLesson(memberId, lessonId);
    }

    @PutMapping(value = "/{studentId}/leave")
    public ResponseEntity<String> removeStudentFromLessonById(@PathVariable Long studentId){
        return studentService.removeStudentFromLessonByStudentId(studentId);
    }

}

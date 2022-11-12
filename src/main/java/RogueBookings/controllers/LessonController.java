package RogueBookings.controllers;


import RogueBookings.dataTransferObjects.LessonDTO;
import RogueBookings.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(path = "lessons")
public class LessonController {

    LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<LessonDTO> getAllLessons(){
        return lessonService.getAllLessons();
    }

    @PostMapping(value = "/{businessId}/new")
    public LessonDTO createNewLessonByBusinessId(@PathVariable Long businessId, @RequestBody LessonDTO lessonDTO){
        return lessonService.createNewLessonBusinessId(businessId, lessonDTO);
    }

    @DeleteMapping(value = "/{lessonId}")
    public ResponseEntity<String> deleteLessonById(@PathVariable Long lessonId){
        return lessonService.deleteLessonById(lessonId);
    }


    @PatchMapping(path = "/{lessonId}")
    public LessonDTO editLesson(@RequestBody LessonDTO lessonDTO, @PathVariable Long lessonId){
        return lessonService.editLesson(lessonDTO, lessonId);
    }

}


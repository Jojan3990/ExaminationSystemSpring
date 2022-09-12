package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Choice;
import com.rightfindpro.become.domain.Course;
import com.rightfindpro.become.dto.Choice.ChoiceResponse;
import com.rightfindpro.become.dto.PageDto;
import com.rightfindpro.become.mapper.ChoiceMapper;
import com.rightfindpro.become.service.ChoiceService;
import com.rightfindpro.become.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/chioce")
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private ChoiceMapper choiceMapper;


    @GetMapping(value = {"", "/"})
    public List<Choice> getCourses() {
        return choiceService.getAllChoices();
    }

  /*  @GetMapping("/all")
    public ResponseEntity<PageDto<Choice>> getAllChoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return new ResponseEntity<>(choiceService.findChoices(page, size), HttpStatus.OK);
    }*/


    @PostMapping("/CreateNewCourse")
    public Choice createNewCourse(@RequestBody Choice choice) {

        return choiceService.createNewChoice(choice);

    }

    @GetMapping("/question/{id}")
    public List<ChoiceResponse> getAllCoursesByQuestion(@PathVariable("id") int id) {
        List<Choice> choices = choiceService.getAllChoicesByQuestion(id);
        return choiceMapper.toDto(choices);

    }
    /*@GetMapping("/question/{id}")
    public List<ChoiceResponse> getAllCoursesByQuestion(@PathVariable("id") int id) {
        List<Choice> choices = choiceService.getAllChoicesByQuestion(id);
        return choiceMapper.toDto(choices);

    }*/

}

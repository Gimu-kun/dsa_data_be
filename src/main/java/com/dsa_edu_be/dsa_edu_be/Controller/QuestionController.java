package com.dsa_edu_be.dsa_edu_be.Controller;

import com.dsa_edu_be.dsa_edu_be.Entity.Question;
import com.dsa_edu_be.dsa_edu_be.Service.QuestionService;
import com.dsa_edu_be.dsa_edu_be.dto.request.Question.QuestionCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.Question.QuestionUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<Question>> getAllQuestion(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Question> questions = questionService.getAllQuestion(pageable);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreationRequest request){
        return questionService.createNewQuestion(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody QuestionUpdateRequest request){
        return questionService.updateQuestion(id,request);
    }
}

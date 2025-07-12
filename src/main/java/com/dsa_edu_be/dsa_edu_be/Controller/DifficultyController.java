package com.dsa_edu_be.dsa_edu_be.Controller;

import com.dsa_edu_be.dsa_edu_be.Entity.Difficulty;
import com.dsa_edu_be.dsa_edu_be.Service.DifficultyService;
import com.dsa_edu_be.dsa_edu_be.dto.request.Difficulty.DifficultyCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.Difficulty.DifficultyUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/difficulty")
@CrossOrigin("*")
public class DifficultyController {
    @Autowired
    DifficultyService difficultyService;

    @GetMapping
    public ResponseEntity<List<Difficulty>> getAllDiffculty(){
        List<Difficulty> diffs = difficultyService.getAllDifficulty();
        return new ResponseEntity<>(diffs,HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<Difficulty> getDifficultyById(@PathVariable String id){
        Optional<Difficulty> optDiff = difficultyService.getDifficultyById(id);
        if (optDiff.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optDiff.get(),HttpStatus.OK);
    };

    @PostMapping
    public ResponseEntity<String> createDifficulty(@Valid @RequestBody DifficultyCreationRequest request){
        return difficultyService.createDifficulty(request);
    };

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateDifficulty(@PathVariable String id,@RequestBody DifficultyUpdateRequest request){
        return difficultyService.updateDifficulty(id,request);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDifficulty(@PathVariable String id){
        return difficultyService.deleteDifficulty(id);
    }
}

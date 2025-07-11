package com.dsa_edu_be.dsa_edu_be.Controller;

import com.dsa_edu_be.dsa_edu_be.Entity.Chapter;
import com.dsa_edu_be.dsa_edu_be.Service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapter")
@CrossOrigin("*")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapter(){
        return chapterService.getAllChapter();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable String id){
        Integer intId = Integer.valueOf(id);
        return chapterService.getChapterById(intId);
    }
}

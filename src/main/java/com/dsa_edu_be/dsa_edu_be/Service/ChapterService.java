package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.Chapter;
import com.dsa_edu_be.dsa_edu_be.Repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    public ResponseEntity<List<Chapter>> getAllChapter(){
        List<Chapter> chapters = chapterRepository.findAll();
        return new ResponseEntity<>(chapters, HttpStatus.OK);
    }

    public ResponseEntity<Chapter> getChapterById(Integer id){
        Optional<Chapter> chapter = chapterRepository.findById(id);
        return chapter.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public boolean checkExistById(Integer id){
        Optional<Chapter> chapter = chapterRepository.findById(id);
        return chapter.isPresent();
    }
}

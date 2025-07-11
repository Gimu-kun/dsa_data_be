package com.dsa_edu_be.dsa_edu_be.Controller;

import com.dsa_edu_be.dsa_edu_be.Entity.Bloom;
import com.dsa_edu_be.dsa_edu_be.Service.BloomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bloom")
@CrossOrigin("*")
public class BloomController {
    @Autowired
    BloomService bloomService;

    @GetMapping
    public ResponseEntity<List<Bloom>> getAllBloom(){
        return bloomService.getAllBloom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bloom> getBloomById(@PathVariable String id){
        Integer intId = Integer.valueOf(id);
        return bloomService.getBloomById(intId);
    }
}

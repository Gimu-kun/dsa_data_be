package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.Bloom;
import com.dsa_edu_be.dsa_edu_be.Repository.BloomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloomService {
    @Autowired
    BloomRepository bloomRepository;
    public ResponseEntity<List<Bloom>> getAllBloom(){
        List<Bloom> blooms = bloomRepository.findAll();
        return new ResponseEntity<>(blooms, HttpStatus.OK);
    }

    public ResponseEntity<Bloom> getBloomById(Integer id){
        Optional<Bloom> bloom = bloomRepository.findById(id);
        return new ResponseEntity<>(bloom.orElse(null),HttpStatus.OK);
    }

    public boolean checkExistById(Integer id){
        Optional<Bloom> bloom = bloomRepository.findById(id);
        return bloom.isPresent();
    }
}

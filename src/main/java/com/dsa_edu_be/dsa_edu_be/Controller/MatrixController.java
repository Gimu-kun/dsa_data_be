package com.dsa_edu_be.dsa_edu_be.Controller;

import com.dsa_edu_be.dsa_edu_be.Entity.Matrix;
import com.dsa_edu_be.dsa_edu_be.Service.MatrixService;
import com.dsa_edu_be.dsa_edu_be.dto.request.Matrix.MatrixCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.Matrix.MatrixUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/matrix")
@CrossOrigin("*")
public class MatrixController {
    @Autowired
    MatrixService matrixService;

    @GetMapping
    public ResponseEntity<List<Matrix>> getAllMatrix(){
        return matrixService.getAllMatrix();
    };

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Matrix>> getMatrixById(@PathVariable String id){
        return matrixService.getMatrixById(id);
    }

    @PostMapping
    public ResponseEntity<String> createMatrix(@Valid @RequestBody MatrixCreationRequest request){
        return matrixService.createMatrix(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Matrix> updateMatrix(@PathVariable String id, @RequestBody MatrixUpdateRequest request){
        return matrixService.updateMatrix(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatrix(@PathVariable String id){
        return matrixService.removeMatrix(id);
    }
}

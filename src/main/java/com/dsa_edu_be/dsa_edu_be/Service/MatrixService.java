package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.Difficulty;
import com.dsa_edu_be.dsa_edu_be.Entity.Matrix;
import com.dsa_edu_be.dsa_edu_be.Repository.MatrixRepository;
import com.dsa_edu_be.dsa_edu_be.Utils.JsonUtils;
import com.dsa_edu_be.dsa_edu_be.dto.request.Matrix.MatrixCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.Matrix.MatrixUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatrixService {
    @Autowired
    MatrixRepository matrixRepository;
    @Autowired
    DifficultyService difficultyService;
    @Autowired
    JsonUtils jsonUtils;
    @Autowired
    UserService userService;

    private boolean checkTitleExist(String title){
        Optional<Matrix> matrix = matrixRepository.findByTitle(title);
        return matrix.isPresent();
    };

    public ResponseEntity<List<Matrix>> getAllMatrix() {
        List<Matrix> matrix = matrixRepository.findAll();
        return new ResponseEntity<>(matrix,HttpStatus.OK);
    }

    public ResponseEntity<Optional<Matrix>> getMatrixById(String id){
        Optional<Matrix> matrix = matrixRepository.findById(id);
        if (matrix.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matrix,HttpStatus.OK);
    }


    public ResponseEntity<String> createMatrix(MatrixCreationRequest request) {
        Optional<Difficulty> difficulty = difficultyService.getDifficultyById(request.getDifficulty_id());
        if (difficulty.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Difficulty not found!");
        }

        if (request.getTitle().isBlank() || request.getTitle() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Title cannot be empty!");
        }

        if(!jsonUtils.isValidJson(request.getKl_matrix())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Knowledge matrix is not JSON format!");
        }

        if(!userService.checkIdExitst(request.getCreated_by())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Creator ID is not found!");
        }

        Matrix matrix = new Matrix(
                request.getDifficulty_id(),
                request.getTitle(),
                request.getKl_matrix(),
                request.getCreated_by(),
                request.getUpdated_by()
        );
        try{
            matrixRepository.save(matrix);
            return new ResponseEntity<>("Create matrix successed",HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.toString());
        }
    }

    public ResponseEntity<Matrix> updateMatrix(String id, MatrixUpdateRequest request) {
        if(id.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Matrix ID cannot be empty!");
        }
        Optional<Matrix> OptMatrix = matrixRepository.findById(id);
        if (OptMatrix.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Matrix is not found!");
        }

        Matrix matrix = OptMatrix.get();
        if(!request.getDifficulty_id().isBlank() || !(request.getDifficulty_id() == null)){
            Optional<Difficulty> difficulty = difficultyService.getDifficultyById(request.getDifficulty_id());
            if (difficulty.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Difficulty not found!");
            }
            matrix.setDifficulty_id(request.getDifficulty_id());
        }

        if(!request.getTitle().isBlank() || !(request.getTitle() == null)){
            if(!checkTitleExist(request.getTitle())){
                matrix.setTitle(request.getTitle());
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Title was existed!");
            }
        }

        if (!request.getKl_matrix().isBlank() || !(request.getKl_matrix() == null)){
            if (jsonUtils.isValidJson(request.getKl_matrix())){
                matrix.setKl_matrix(request.getKl_matrix());
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Knowledge matrix is not valid JSON format!");
            }
        }

        if (request.getUpdated_by().isBlank() || request.getUpdated_by() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Updater cannot be empty!");
        }else{
            if (userService.checkIdExitst(request.getUpdated_by())){
                matrix.setUpdated_by(request.getUpdated_by());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Updater ID not found!");
            }
        }
        matrix.setUpdated_at(LocalDateTime.now());

        try{
            matrixRepository.save(matrix);
            return new ResponseEntity<>(matrix, HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.toString());
        }
    }

    public ResponseEntity<String> removeMatrix(String id){
        try{
            matrixRepository.deleteById(id);
            return new ResponseEntity<>("Delete matrix successfully",HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.toString());
        }
    };
}

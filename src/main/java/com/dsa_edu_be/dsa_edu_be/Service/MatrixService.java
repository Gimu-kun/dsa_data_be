package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.Difficulty;
import com.dsa_edu_be.dsa_edu_be.Entity.Matrix;
import com.dsa_edu_be.dsa_edu_be.Repository.MatrixRepository;
import com.dsa_edu_be.dsa_edu_be.Utils.ArrayUtils;
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
    @Autowired
    ArrayUtils arrayUtils;

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
        //Validate difficulty id
        Optional<Difficulty> difficulty = difficultyService.getDifficultyById(request.getDifficulty_id());
        if (difficulty.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Difficulty not found!");
        }


        //Validate knowledge matrix
        if(arrayUtils.isJsonArray(request.getKl_matrix())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Knowledge matrix is not JSON Array format!");
        }

        //Validate creator id
        if(!userService.checkIdExitst(request.getCreated_by())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Creator ID is not found!");
        }

        //Create new object
        Matrix matrix = new Matrix(
                request.getDifficulty_id(),
                request.getTitle(),
                request.getKl_matrix(),
                request.getCreated_by()
        );
        try{
            //Save object to database
            matrixRepository.save(matrix);
            return new ResponseEntity<>("Create matrix successfully",HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.toString());
        }
    }

    public ResponseEntity<Matrix> updateMatrix(String id, MatrixUpdateRequest request) {
        //Find object matrix in database
        Optional<Matrix> OptMatrix = matrixRepository.findById(id);
        if (OptMatrix.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Matrix is not found!");
        }
        Matrix matrix = OptMatrix.get();

        //Validate difficulty id
        if(request.getDifficulty_id() != null){
            Optional<Difficulty> difficulty = difficultyService.getDifficultyById(request.getDifficulty_id());
            if (difficulty.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Difficulty not found!");
            }
            matrix.setDifficulty_id(request.getDifficulty_id());
        }

        //Validate title
        if(request.getTitle() != null && !request.getTitle().isBlank()){
            if(!checkTitleExist(request.getTitle())){
                matrix.setTitle(request.getTitle());
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Title was existed!");
            }
        }

        //Validate knowledge matrix
        if (!request.getKl_matrix().isBlank() && request.getKl_matrix() != null){
            if (arrayUtils.isJsonArray(request.getKl_matrix())){
                matrix.setKl_matrix(request.getKl_matrix());
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Knowledge matrix is not valid JSON format!");
            }
        }

        //Validate updater id
        if (userService.checkIdExitst(request.getUpdated_by())){
            matrix.setUpdated_by(request.getUpdated_by());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Updater ID not found!");
        }

        //Set updated time
        matrix.setUpdated_at(LocalDateTime.now());
        try{
            //Save update detail to database
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

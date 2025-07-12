package com.dsa_edu_be.dsa_edu_be.dto.request.Matrix;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MatrixCreationRequest {
    @NotNull(message = "Difficulty id cannot be empty")
    private String difficulty_id;

    @NotNull(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Knowledge matrix cannot be empty")
    @NotBlank(message = "Knowledge matrix cannot be blank")
    private String kl_matrix;

    @NotNull(message = "Creator id cannot be empty")
    @NotBlank(message = "Creator id cannot be blank")
    private String created_by;

    public String getDifficulty_id() {
        return difficulty_id;
    }

    public void setDifficulty_id(String difficulty_id) {
        this.difficulty_id = difficulty_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKl_matrix() {
        return kl_matrix;
    }

    public void setKl_matrix(String kl_matrix) {
        this.kl_matrix = kl_matrix;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

}

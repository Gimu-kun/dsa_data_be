package com.dsa_edu_be.dsa_edu_be.dto.request.Matrix;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MatrixUpdateRequest {
    private String difficulty_id;
    private String title;
    private String kl_matrix;

    @NotNull(message = "Updater id cannot be empty")
    @NotBlank(message = "Updater id cannot be blank")
    private String updated_by;

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

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}

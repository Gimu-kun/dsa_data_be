package com.dsa_edu_be.dsa_edu_be.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Matrix {
    @Id
    private String id;
    private String difficulty_id;
    private String title;
    private String kl_matrix;
    private String created_by;
    private String updated_by;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Matrix(){}

    public Matrix( String difficulty_id, String title, String kl_matrix, String created_by, String updated_by) {
        this.id = UUID.randomUUID().toString();
        this.difficulty_id = difficulty_id;
        this.title = title;
        this.kl_matrix = kl_matrix;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}

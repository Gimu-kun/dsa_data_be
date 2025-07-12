package com.dsa_edu_be.dsa_edu_be.dto.request.Difficulty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class DifficultyCreationRequest {
    @NotNull(message = "Title cannot be empty")
    @Size(min = 8, max = 255, message = "Title must have between 8-255 characters")
    private String title;

    @NotNull(message = "Bloom percentage cannot be empty")
    @Min(0)
    @Max(100)
    private Float r_percent;

    @NotNull(message = "Bloom percentage cannot be empty")
    @Min(0)
    @Max(100)
    private Float u_percent;

    @NotNull(message = "Bloom percentage cannot be empty")
    @Min(0)
    @Max(100)
    private Float ap_percent;

    @NotNull(message = "Bloom percentage cannot be empty")
    @Min(0)
    @Max(100)
    private Float an_percent;

    @NotNull(message = "Diff range cannot be empty")
    private String diff_range;

    @NotNull(message = "Disc range cannot be empty")
    private String disc_range;

    @NotNull(message = "Chapter range cannot be empty")
    private String chapter_range;

    @NotNull(message = "Question type cannot be empty")
    private String question_types;

    @NotNull(message = "Creator cannot be empty")
    private String created_by;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getR_percent() {
        return r_percent;
    }

    public void setR_percent(Float r_percent) {
        this.r_percent = r_percent;
    }

    public Float getU_percent() {
        return u_percent;
    }

    public void setU_percent(Float u_percent) {
        this.u_percent = u_percent;
    }

    public Float getAp_percent() {
        return ap_percent;
    }

    public void setAp_percent(Float ap_percent) {
        this.ap_percent = ap_percent;
    }

    public Float getAn_percent() {
        return an_percent;
    }

    public void setAn_percent(Float an_percent) {
        this.an_percent = an_percent;
    }

    public String getDiff_range() {
        return diff_range;
    }

    public void setDiff_range(String diff_range) {
        this.diff_range = diff_range;
    }

    public String getDisc_range() {
        return disc_range;
    }

    public void setDisc_range(String disc_range) {
        this.disc_range = disc_range;
    }

    public String getChapter_range() {
        return chapter_range;
    }

    public void setChapter_range(String chapter_range) {
        this.chapter_range = chapter_range;
    }

    public String getQuestion_types() {
        return question_types;
    }

    public void setQuestion_types(String question_types) {
        this.question_types = question_types;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

}

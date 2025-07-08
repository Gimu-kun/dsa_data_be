package com.dsa_edu_be.dsa_edu_be.dto.request.Difficulty;

public class DifficultyUpdateRequest {
    private String title;
    private Float r_percent;
    private Float u_percent;
    private Float ap_percent;
    private Float an_percent;
    private String diff_range;
    private String disc_range;
    private String chapter_range;
    private String question_types;
    private String updated_by;

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

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String update_by) {
        this.updated_by = update_by;
    }
}

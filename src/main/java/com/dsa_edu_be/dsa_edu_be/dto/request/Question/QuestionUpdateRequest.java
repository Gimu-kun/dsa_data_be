package com.dsa_edu_be.dsa_edu_be.dto.request.Question;

import com.dsa_edu_be.dsa_edu_be.Enum.QuestionStatus;
import com.dsa_edu_be.dsa_edu_be.Enum.QuestionTypes;

import java.time.LocalDateTime;

public class QuestionUpdateRequest {
    private QuestionTypes type;
    private String content;
    private String image;
    private String answers;
    private String correct_answer;
    private Float partial_credit = 0F;
    private Float tolerance_range = 0F;
    private String syntax_variant = null;
    private String format_variant = null;
    private String synonyms = null;
    private Integer bloom_level;
    private Float diff_level = 0.5F;
    private Float disc_level = 0.5F;
    private Integer chapter_id;
    private QuestionStatus status;
    private String updated_by;

    public QuestionTypes getType() {
        return type;
    }

    public void setType(QuestionTypes type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public Float getPartial_credit() {
        return partial_credit;
    }

    public void setPartial_credit(Float partial_credit) {
        this.partial_credit = partial_credit;
    }

    public Float getTolerance_range() {
        return tolerance_range;
    }

    public void setTolerance_range(Float tolerance_range) {
        this.tolerance_range = tolerance_range;
    }

    public String getSyntax_variant() {
        return syntax_variant;
    }

    public void setSyntax_variant(String syntax_variant) {
        this.syntax_variant = syntax_variant;
    }

    public String getFormat_variant() {
        return format_variant;
    }

    public void setFormat_variant(String format_variant) {
        this.format_variant = format_variant;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public Integer getBloom_level() {
        return bloom_level;
    }

    public void setBloom_level(Integer bloom_level) {
        this.bloom_level = bloom_level;
    }

    public Float getDiff_level() {
        return diff_level;
    }

    public void setDiff_level(Float diff_level) {
        this.diff_level = diff_level;
    }

    public Float getDisc_level() {
        return disc_level;
    }

    public void setDisc_level(Float disc_level) {
        this.disc_level = disc_level;
    }

    public Integer getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Integer chapter_id) {
        this.chapter_id = chapter_id;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionStatus status) {
        this.status = status;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}

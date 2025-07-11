package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.Question;
import com.dsa_edu_be.dsa_edu_be.Enum.QuestionStatus;
import com.dsa_edu_be.dsa_edu_be.Enum.QuestionTypes;
import com.dsa_edu_be.dsa_edu_be.Repository.QuestionRepository;
import com.dsa_edu_be.dsa_edu_be.Utils.ArrayUtils;
import com.dsa_edu_be.dsa_edu_be.dto.request.Question.QuestionCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.Question.QuestionUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ArrayUtils arrayUtils;
    @Autowired
    UserService userService;
    @Autowired
    BloomService bloomService;
    @Autowired
    ChapterService chapterService;

    public Page<Question> getAllQuestion(Pageable pageable){
        return questionRepository.findAll(pageable);
    }

    public ResponseEntity<List<Question>> getAllQuestion(){
        List<Question> questions = questionRepository.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Question> createNewQuestion(QuestionCreationRequest request) {
        try{
            Question question = new Question();
            if (request.getType().toString().isBlank() || request.getType() == null){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Question type is not valid");
            }
            question.setType(request.getType());

            if (request.getContent().isBlank() || request.getContent() == null){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Content cannot be null!");
            }
            question.setContent(request.getContent());

            if (request.getImage().isBlank() || request.getImage() == null){
                question.setImage(null);
            }else{
                question.setImage(request.getImage());
            }

            if (!arrayUtils.isJsonArray(request.getAnswers())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Answers is not JSON Array format");
            }
            question.setAnswers(request.getAnswers());

            if (!arrayUtils.isJsonArray(request.getCorrect_answer())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Question type is not JSON Array format");
            }
            question.setCorrect_answer(request.getCorrect_answer());

            if (request.getPartial_credit().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Partial credit must be a number");
            }
            question.setPartial_credit(request.getPartial_credit());

            if (request.getTolerance_range().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Tolerance range must be a number");
            }
            question.setTolerance_range(request.getTolerance_range());

            if (request.getDiff_level().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Diff level must be a number");
            }
            question.setDiff_level(request.getDiff_level());

            if (request.getDisc_level().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Disc level must be a number");
            }
            question.setDisc_level(request.getDisc_level());

            if (!arrayUtils.isJsonArray(request.getSyntax_variant())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Syntax_variant is not JSON Array format");
            }
            question.setSyntax_variant(request.getSyntax_variant());

            if (!arrayUtils.isJsonArray(request.getFormat_variant())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Format_variant is not JSON Array format");
            }
            question.setFormat_variant(request.getFormat_variant());

            if (!arrayUtils.isJsonArray(request.getSynonyms())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Synonyms is not JSON Array format");
            }
            question.setSynonyms(request.getSynonyms());

            if(!bloomService.checkExistById(request.getBloom_level())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Bloom level not valid!");
            }
            question.setBloom_level(request.getBloom_level());

            if(!chapterService.checkExistById(request.getChapter_id())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Chapter not valid!");
            }
            question.setChapter_id(request.getChapter_id());

            if (request.getStatus().toString().isBlank() || request.getStatus() == null){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Status is not valid");
            }
            question.setStatus(request.getStatus());

            if(!userService.checkIdExitst(request.getCreated_by())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Creator id is not valid");
            }
            question.setCreated_by(request.getCreated_by());
            question.setUpdated_by(request.getCreated_by());
            questionRepository.save(question);
            return new ResponseEntity<>(question,HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Some Error When Create A New Question");
        }
    }

    public ResponseEntity<Question> updateQuestion(String id, QuestionUpdateRequest request) {
        try {
            Optional<Question> optQuestion = questionRepository.findById(id);
            if (optQuestion.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Question id not found!");
            }
            Question question = optQuestion.get();

            if (request.getType().toString().isBlank() || request.getType() == null){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Question type is not valid");
            }
            question.setType(request.getType());

            if (request.getContent().isBlank() || request.getContent() == null){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Content cannot be null!");
            }
            question.setContent(request.getContent());

            if (request.getImage().isBlank() || request.getImage() == null){
                question.setImage(null);
            }else{
                question.setImage(request.getImage());
            }

            if (!arrayUtils.isJsonArray(request.getAnswers())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Answers is not JSON Array format");
            }
            question.setAnswers(request.getAnswers());

            if (!arrayUtils.isJsonArray(request.getCorrect_answer())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Question type is not JSON Array format");
            }
            question.setCorrect_answer(request.getCorrect_answer());

            if (request.getPartial_credit().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Partial credit must be a number");
            }
            question.setPartial_credit(request.getPartial_credit());

            if (request.getTolerance_range().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Tolerance range must be a number");
            }
            question.setTolerance_range(request.getTolerance_range());

            if (request.getDiff_level().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Diff level must be a number");
            }
            question.setDiff_level(request.getDiff_level());

            if (request.getDisc_level().isNaN()){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Disc level must be a number");
            }
            question.setDisc_level(request.getDisc_level());

            if (!arrayUtils.isJsonArray(request.getSyntax_variant())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Syntax_variant is not JSON Array format");
            }
            question.setSyntax_variant(request.getSyntax_variant());

            if (!arrayUtils.isJsonArray(request.getFormat_variant())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Format_variant is not JSON Array format");
            }
            question.setFormat_variant(request.getFormat_variant());

            if (!arrayUtils.isJsonArray(request.getSynonyms())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Synonyms is not JSON Array format");
            }
            question.setSynonyms(request.getSynonyms());

            if(!bloomService.checkExistById(request.getBloom_level())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Bloom level not valid!");
            }
            question.setBloom_level(request.getBloom_level());

            if(!chapterService.checkExistById(request.getChapter_id())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Chapter not valid!");
            }
            question.setChapter_id(request.getChapter_id());

            if (request.getStatus().toString().isBlank() || request.getStatus() == null){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Status is not valid");
            }
            question.setStatus(request.getStatus());

            if(!userService.checkIdExitst(request.getUpdated_by())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Updater id is not valid");
            }

            question.setUpdated_by(request.getUpdated_by());
            question.setUpdated_at(LocalDateTime.now());
            questionRepository.save(question);
            return new ResponseEntity<>(question,HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new RuntimeException(e);
        }
    }
}

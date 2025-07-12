package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.Difficulty;
import com.dsa_edu_be.dsa_edu_be.Repository.DifficultyRepository;
import com.dsa_edu_be.dsa_edu_be.dto.request.Difficulty.DifficultyCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.Difficulty.DifficultyUpdateRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class DifficultyService {
    @Autowired
    DifficultyRepository difficultyRepository;
    @Autowired
    UserService userService;

    public List<Difficulty> getAllDifficulty(){
        return difficultyRepository.findAll();
    }

    public Optional<Difficulty> getDifficultyById(String id){
        return difficultyRepository.findById(id)  ;
    }

    private boolean checkRangeFormat(String range) {
        final Pattern SIMPLE_PATTERN_LEFT = Pattern.compile("^x\\s*(<|>|<=|>=)\\s*\\d+(\\.\\d+)?$");
        final Pattern RANGE_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?\\s*(<|>|<=|>=)\\s*x\\s*(<|>|<=|>=)\\s*\\d+(\\.\\d+)?$");
        return SIMPLE_PATTERN_LEFT.matcher(range).matches() || RANGE_PATTERN.matcher(range).matches();
    }

    private boolean checkChapterListFormat(String list){
        try{
            final Pattern CHAPTER_PATTERN = Pattern.compile("^C[1-6]$");
            List<String> StrList = List.of(list.split(","));
            for(String s:StrList){
                if (!CHAPTER_PATTERN.matcher(s.trim()).matches()){
                    return false;
                }
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    };

    private boolean checkQuestionListFormat(String list){
      try{
          Set<String> validTypes = new HashSet<>(Arrays.asList(
                  "mcq", "fn", "fns", "fps", "fss", "mp", "pe", "fe"
          ));
          String[] StrList = list.split(",");
          for(String s:StrList){
              if(!validTypes.contains(s)){
                  return false;
              }
          }
          return true;
      }catch(Exception ex){
          return false;
      }
    };

    private boolean isStringArray(String raw){
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(raw);
            for(JsonNode element : node){
                if (!element.isTextual()) return false;
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public ResponseEntity<String> updateDifficulty(String id,DifficultyUpdateRequest request){
        try{
            //Validate and find relate difficulty
            Optional<Difficulty> OptDiff = getDifficultyById(id);
            if (OptDiff.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found this difficulty!");
            }
            Difficulty diff = OptDiff.get();

            //Validate and update title
            if (!request.getTitle().isBlank() || request.getTitle() != null){
                diff.setTitle(request.getTitle());
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Title cannot be empty!");
            }

            //Validate and update percentage of blooms
            Map<String,Float> percentage = Map.of(
                    "R",Optional.ofNullable(request.getR_percent()).orElse(diff.getR_percent()),
                    "U",Optional.ofNullable(request.getU_percent()).orElse(diff.getU_percent()),
                    "AN",Optional.ofNullable(request.getAn_percent()).orElse(diff.getAn_percent()),
                    "AP",Optional.ofNullable(request.getAp_percent()).orElse(diff.getAp_percent())
            );
            double total = percentage.values().stream().mapToDouble(Float::doubleValue).sum();

            //Validate all bloom percentage must have summary equal 100%
            if (Math.abs(total - 100.0) < 0.001){
                diff.setR_percent(percentage.get("R"));
                diff.setU_percent(percentage.get("U"));
                diff.setAp_percent(percentage.get("AP"));
                diff.setAn_percent(percentage.get("AN"));
            }else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Total percent of R U AP AN need exactly 100%!");
            }

            //Validate and update diff range format
            if (request.getDiff_range() != null){
                if (checkRangeFormat(request.getDiff_range())){
                    diff.setDiff_range(request.getDiff_range());
                }else{
                    throw new ResponseStatusException(HttpStatus.CONFLICT,"Diff Range Format is not valid!");
                }
            }

            //Validate and update disc range format
            if (request.getDisc_range() != null){
                if (checkRangeFormat(request.getDisc_range())){
                    diff.setDisc_range(request.getDisc_range());
                }else{
                    throw new ResponseStatusException(HttpStatus.CONFLICT,"Disc Range Format is not valid!");
                }
            }

            //Validate and set chapter_range
            if (request.getChapter_range() != null){
                if (checkChapterListFormat(request.getChapter_range())){
                    diff.setChapter_range(request.getChapter_range());
                }else {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Chapter List is not valid!");
                }
            }

            //Validate Question list format
            if (request.getQuestion_types() != null){
                if(checkQuestionListFormat(request.getQuestion_types())){
                    diff.setQuestion_types(request.getQuestion_types());
                }else{
                    throw new ResponseStatusException(HttpStatus.CONFLICT,"Question type format is not valid!");
                }
            }


            //Check updater existed
            if (!userService.checkIdExitst(request.getUpdated_by())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Updater not found!");
            }

            //Set updater detail
            diff.setUpdated_by(request.getUpdated_by());
            diff.setUpdate_at(LocalDateTime.now());

            //Save updated difficulty to database
            difficultyRepository.save(diff);
            return new ResponseEntity<>("Update difficulty successfully",HttpStatus.OK);
        }catch(Exception ex){
            System.out.println(ex.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Update Error");
        }
    }

    public ResponseEntity<String> createDifficulty(DifficultyCreationRequest request){
        try{
            Difficulty diff = new Difficulty();

            //Generate UUID
            diff.setId(UUID.randomUUID().toString());

            //Validate and set percentage of bloom
            if (!(request.getR_percent() + request.getU_percent() + request.getAp_percent() + request.getAn_percent() == 100)){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Total percent of R U AP AN need exactly 100%!");
            }
            diff.setR_percent(request.getR_percent());
            diff.setU_percent(request.getU_percent());
            diff.setAp_percent(request.getAp_percent());
            diff.setAn_percent(request.getAn_percent());

            //Validate and set range of diff , disc
            if(!checkRangeFormat(request.getDisc_range())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Disc Range Format is not valid!");
            }

            if (!checkRangeFormat(request.getDiff_range())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Diff Range Format is not valid!");
            }

            diff.setDiff_range(request.getDiff_range());
            diff.setDisc_range(request.getDisc_range());

            //Validate and set chapter_range
            if (!checkChapterListFormat(request.getChapter_range())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Chapter List is not valid!");
            }
            diff.setChapter_range(request.getChapter_range());

            //Validate and set question types
            if(!checkQuestionListFormat(request.getQuestion_types())){
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Question type format is not valid!");
            }
            diff.setQuestion_types(request.getQuestion_types());

            //Validate and set Creator and updater
            if (!userService.checkIdExitst(request.getCreated_by())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Creator not found!");
            }
            diff.setUpdated_by(request.getCreated_by());
            diff.setCreated_by(request.getCreated_by());
            diff.setCreated_at(LocalDateTime.now());
            diff.setUpdate_at(LocalDateTime.now());

            //Save new difficulty to database
            difficultyRepository.save(diff);
            return new ResponseEntity<>("Create difficulty successfully",HttpStatus.OK);
        }catch(Exception ex){
            System.out.println(ex.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Create difficulty error");
        }
    }

    public ResponseEntity<String> deleteDifficulty(String id) {
        try{
            difficultyRepository.deleteById(id);
            return new ResponseEntity<>("Delete difficulty successfully",HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.toString());
        }
    }
}

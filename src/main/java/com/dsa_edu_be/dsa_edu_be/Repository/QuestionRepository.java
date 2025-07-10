package com.dsa_edu_be.dsa_edu_be.Repository;

import com.dsa_edu_be.dsa_edu_be.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,String> {
}

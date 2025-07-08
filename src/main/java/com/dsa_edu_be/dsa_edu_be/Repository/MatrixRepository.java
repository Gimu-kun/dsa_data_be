package com.dsa_edu_be.dsa_edu_be.Repository;

import com.dsa_edu_be.dsa_edu_be.Entity.Matrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix,String> {

    Optional<Matrix> findByTitle(String title);
}

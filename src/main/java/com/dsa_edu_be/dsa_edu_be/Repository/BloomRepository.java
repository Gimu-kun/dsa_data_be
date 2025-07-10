package com.dsa_edu_be.dsa_edu_be.Repository;

import com.dsa_edu_be.dsa_edu_be.Entity.Bloom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloomRepository extends JpaRepository<Bloom,Integer> {
}

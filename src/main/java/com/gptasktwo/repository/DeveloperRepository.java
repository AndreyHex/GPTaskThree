package com.gptasktwo.repository;

import com.gptasktwo.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {


    Developer deleteById(long id);
    List<Developer> findByName(String name);
    boolean existsById(long id);
    boolean existsByEmail(String email);

}

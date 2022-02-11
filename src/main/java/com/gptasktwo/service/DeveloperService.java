package com.gptasktwo.service;

import com.gptasktwo.entity.Developer;

import java.util.List;

public interface DeveloperService {

    Developer create(Developer developer);
    Developer update(long id, Developer developer);
    Developer delete(long id);
    Developer findById(long id);
    List<Developer> findByName(String name);

}

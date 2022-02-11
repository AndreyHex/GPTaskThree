package com.gptasktwo.service;

import com.gptasktwo.entity.Developer;
import com.gptasktwo.exception.DeveloperNotFoundException;
import com.gptasktwo.exception.EmailAlreadyInUseException;
import com.gptasktwo.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Override
    public Developer create(Developer developer) {
        ifAlreadyExistsThrowException(developer.getEmail());
        return developerRepository.save(developer);
    }

    @Override
    public Developer update(long id, Developer developer) {
        Developer dev = developerRepository.findById(id).orElseThrow(() -> new DeveloperNotFoundException("Developer not found."));

        if(!dev.getEmail().equals(developer.getEmail()))
            ifAlreadyExistsThrowException(developer.getEmail());

        developer.setId(id);
        return developerRepository.save(developer);
    }

    @Override
    public Developer delete(long id) {
        ifNotExistsThrowException(id);
        return developerRepository.deleteById(id);
    }

    @Override
    public List<Developer> findByName(String name) {
        List<Developer> developerList = developerRepository.findByName(name);
        if(developerList.isEmpty()) throw new DeveloperNotFoundException("Developer not found.");
        return developerList;
    }

    @Override
    public Developer findById(long id) {
        return developerRepository
                .findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer not found."));
    }

    private void ifNotExistsThrowException(long id) {
        if(!developerRepository.existsById(id)) throw new DeveloperNotFoundException("Developer not found.");
    }
    private void ifAlreadyExistsThrowException(String email) {
        if(developerRepository.existsByEmail(email)) throw new EmailAlreadyInUseException("Email "+email+" is already in use.");
    }
}

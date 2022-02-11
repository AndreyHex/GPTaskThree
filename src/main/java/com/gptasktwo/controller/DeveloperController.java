package com.gptasktwo.controller;

import com.gptasktwo.entity.Developer;
import com.gptasktwo.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(developerService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        return ResponseEntity.ok(developerService.findByName(name));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.create(developer));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.update(id, developer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(developerService.delete(id));
    }
}

package com.gptasktwo.controller;

import com.gptasktwo.entity.Developer;
import com.gptasktwo.security.meta.IsAdmin;
import com.gptasktwo.security.meta.IsAuthorized;
import com.gptasktwo.service.DeveloperService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = Developer.class)))
    @IsAuthorized
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok(developerService.findById(id));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = Developer.class, type = "array")))
    @IsAuthorized
    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByName(@PathVariable String name) {
        return ResponseEntity.ok(developerService.findByName(name));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = Developer.class)))
    @IsAdmin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.create(developer));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = Developer.class)))
    @IsAdmin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.update(id, developer));
    }

    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    schema = @Schema(implementation = Developer.class)))
    @IsAdmin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(developerService.delete(id));
    }
}

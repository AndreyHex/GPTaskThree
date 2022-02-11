package com.gptasktwo.service;

import com.gptasktwo.entity.Developer;
import com.gptasktwo.exception.DeveloperNotFoundException;
import com.gptasktwo.exception.EmailAlreadyInUseException;
import com.gptasktwo.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceImplTest {

    @Mock
    private DeveloperRepository developerRepository;

    private DeveloperServiceImpl developerService;

    @BeforeEach
    void init() {
        developerService = new DeveloperServiceImpl(developerRepository);
    }

    @Test
    void createValidDeveloper() {
        when(developerRepository.existsByEmail(anyString())).thenReturn(false);
        when(developerRepository.save(any(Developer.class))).then(
                args -> {
                    Developer dev = args.getArgument(0);
                    dev.setId(1);
                    return dev;
                }
        );

        Developer dev = new Developer();
        dev.setEmail("test@test");
        dev.setName("Test Name");
        Developer createdDev = developerService.create(dev);

        assertThat(createdDev.getId()).isEqualTo(1);
    }

    @Test
    void throwExceptionWhenEmailAlreadyInUse() {
        when(developerRepository.existsByEmail(anyString())).thenReturn(true);

        Developer dev = new Developer();
        dev.setEmail("test@test");
        dev.setName("Test Name");

        assertThrows(
                EmailAlreadyInUseException.class,
                () -> developerService.create(dev),
                "Expected EmailAlreadyInUseException."
        );
    }

    @Test
    void throwExceptionWhenUpdateNonExistentDeveloper() {
        when(developerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Developer dev = new Developer();
        dev.setEmail("test@test");
        dev.setName("Test Name");

        assertThrows(
                DeveloperNotFoundException.class,
                () -> developerService.update(1, dev),
                "Expected DeveloperNotFoundException."
        );
    }

    @Test
    void throwExceptionWhenDeleteNonExistentDeveloper() {
        when(developerRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(
                DeveloperNotFoundException.class,
                () -> developerService.delete(1),
                "Expected DeveloperNotFoundException."
        );
    }
}
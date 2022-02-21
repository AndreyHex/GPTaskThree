package com.gptasktwo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Invalid email format.")
    @Column(unique = true)
    @Schema(example = "myemail@mymail.com")
    private String email;

    @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters.")
    @Pattern(regexp = "\\D\\w*", message = "First name character must be a letter.")
    @Schema(example = "John Weak")
    private String name;
}

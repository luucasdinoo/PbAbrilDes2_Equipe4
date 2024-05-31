package br.com.backend.equipe4.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String bio;
    @NotBlank
    private String nickname;
    @NotBlank
    @Email
    private String email;

    private String password;

    @Past
    private LocalDate birthDate;
}
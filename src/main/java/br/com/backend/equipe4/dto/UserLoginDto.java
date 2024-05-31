package br.com.backend.equipe4.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserLoginDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

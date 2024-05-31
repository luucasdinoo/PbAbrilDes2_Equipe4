package br.com.backend.equipe4.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PostCreateDto {

    @NotBlank
    private String text;
}

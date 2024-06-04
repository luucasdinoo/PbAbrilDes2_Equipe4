package br.com.backend.equipe4.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserPublicDto {

    private UUID id;
    private String username;
}

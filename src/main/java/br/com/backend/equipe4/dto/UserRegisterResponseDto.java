package br.com.backend.equipe4.dto;


import br.com.backend.equipe4.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserRegisterResponseDto {

    private UUID id;
    private String fullname;
    private String username;
    private String summary;
    private List<String> follows = new ArrayList<>();
    private List<String> followers = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String createdAt ;
    private String updatedAt;
    private String role;

}

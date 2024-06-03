package br.com.backend.equipe4.dto;

import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
public class ProfileResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String summary;
    private List<UserPublicDto> follows;
    private List<UserPublicDto> followers;
    private List<Post> posts;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}

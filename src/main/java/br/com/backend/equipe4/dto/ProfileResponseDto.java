package br.com.backend.equipe4.dto;

import br.com.backend.equipe4.entity.Post;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class ProfileResponseDto {

    private String username;
    private String summary;
    private List<String> follows = new ArrayList<>();
    private List<String> followers = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

}

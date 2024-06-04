package br.com.backend.equipe4.dto;

import br.com.backend.equipe4.entity.Post;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class PostResponseDto implements Serializable {


    private String id;
    private String userId;
    private String authorId;
    private String author;
    private Post post;

}

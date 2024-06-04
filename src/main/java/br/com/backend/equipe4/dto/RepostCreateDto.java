package br.com.backend.equipe4.dto;

import br.com.backend.equipe4.entity.Post;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class RepostCreateDto {

    private Long id;
    private Long postId;
    private UUID userId;
    private String authorName;
    private Post post;

}

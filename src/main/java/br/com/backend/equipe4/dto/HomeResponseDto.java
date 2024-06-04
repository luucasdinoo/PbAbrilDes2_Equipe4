package br.com.backend.equipe4.dto;

import br.com.backend.equipe4.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HomeResponseDto {


    private String id;
    private String userId;
    private String authorId;
    private int likes;
    private int retweets;
    private int numberComments;
    private String createdAt;
    private String updatedAt;

}

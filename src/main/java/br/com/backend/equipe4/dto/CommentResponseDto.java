package br.com.backend.equipe4.dto;


import lombok.Data;

@Data
public class CommentResponseDto {

    private String id;
    private String authorId;
    private String comment;
    private int likes;
    private int numberComments;
}

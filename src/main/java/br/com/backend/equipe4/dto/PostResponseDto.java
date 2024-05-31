package br.com.backend.equipe4.dto;

import br.com.backend.equipe4.entity.Author;
import br.com.backend.equipe4.entity.Comments;

import java.util.List;

public class PostResponseDto {

    private Author author;
    private String text;
    private int likes;
    private int retweets;
    private int numberComments;
    private List<Comments> comments;
}

package br.com.backend.equipe4.dto.mapper;

import br.com.backend.equipe4.dto.CommentResponseDto;
import br.com.backend.equipe4.dto.CommnetCreateDto;
import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.entity.Comments;
import br.com.backend.equipe4.entity.Post;
import org.modelmapper.ModelMapper;

public class CommentMapper {

    public static Comments toComment(CommnetCreateDto commentDto){
        return new ModelMapper().map(commentDto, Comments.class);
    }
    public static CommentResponseDto toCommnetCreateDto(Comments comments){
        return new ModelMapper().map(comments, CommentResponseDto.class);
    }
}

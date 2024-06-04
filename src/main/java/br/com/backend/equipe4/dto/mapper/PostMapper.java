package br.com.backend.equipe4.dto.mapper;


import br.com.backend.equipe4.dto.HomeResponseDto;
import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.dto.PostResponseDto;
import br.com.backend.equipe4.entity.Author;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.User;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

public class PostMapper {

    public static Post toPost(PostCreateDto dto){
        return new ModelMapper().map(dto, Post.class);
    }

    public static PostResponseDto toDto(Post post){
        return new ModelMapper().map(post, PostResponseDto.class);
    }

    public static Author toAuthor(User user){
        return new ModelMapper().map(user, Author.class);
    }

    public static HomeResponseDto toHome(Post post){
        return new ModelMapper().map(post, HomeResponseDto.class);
    }


}

package br.com.backend.equipe4.dto.mapper;


import br.com.backend.equipe4.dto.PostCreateDto;
import br.com.backend.equipe4.dto.RepostCreateDto;
import br.com.backend.equipe4.entity.Post;
import br.com.backend.equipe4.entity.Repost;
import org.modelmapper.ModelMapper;

public class RepostMapper {

    public static Repost toRepost(RepostCreateDto dto){
        return new ModelMapper().map(dto, Repost.class);
    }

    public static RepostCreateDto toDto(Repost repost, Post post){
        RepostCreateDto dto = new RepostCreateDto();
        dto.setId(repost.getId());
        dto.setPostId(repost.getPostId());
        dto.setAuthorName(repost.getAuthorName());
        dto.setUserId(repost.getUserId());
        dto.setPost(post);

        return dto;
    }
}

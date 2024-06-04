package br.com.backend.equipe4.dto.mapper;



import br.com.backend.equipe4.dto.RepostCreateDto;
import br.com.backend.equipe4.entity.Post;;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.modelmapper.ModelMapper;

public class RepostMapper {

    @JsonIgnore
    public static RepostCreateDto toDto(Post post){
        return new ModelMapper().map(post, RepostCreateDto.class);
    }

}

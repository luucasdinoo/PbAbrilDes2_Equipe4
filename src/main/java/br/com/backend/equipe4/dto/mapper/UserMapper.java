package br.com.backend.equipe4.dto.mapper;
import br.com.backend.equipe4.dto.ProfileResponseDto;
import br.com.backend.equipe4.dto.UserPublicDto;
import br.com.backend.equipe4.dto.UserRegisterResponseDto;
import br.com.backend.equipe4.entity.User;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class UserMapper {
    public static User toUser(@Valid User RegisterResponseDto) {
        return new ModelMapper().map(RegisterResponseDto, User.class);

    }

    public static UserRegisterResponseDto toDto(User user) {

        String role = user.getRole().name().substring("ROLE_".length());
        UUID id = user.getId();
        String fullName = user.getFirstName()+ " " + user.getLastName();
        PropertyMap<User, UserRegisterResponseDto> props = new PropertyMap<User, UserRegisterResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
                map().setId(id);
                map().setFullname(fullName);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserRegisterResponseDto.class);
    }

    public static ProfileResponseDto toProfileDto(User user) {

        ModelMapper mapper = new ModelMapper();
        return  mapper.map(user, ProfileResponseDto.class);
    }

}


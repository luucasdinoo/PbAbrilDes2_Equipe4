package br.com.backend.equipe4.dto.mapper;
import br.com.backend.equipe4.dto.UserDto;
import br.com.backend.equipe4.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setSummary(userDto.getBio());
        user.setUsername(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBirthdate(userDto.getBirthDate());
        return user;
    }
}


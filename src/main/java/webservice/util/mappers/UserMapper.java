package webservice.util.mappers;

import org.springframework.stereotype.Service;
import webservice.dto.RegistrationDTO;
import webservice.dto.UserDTO;
import webservice.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper extends ClassMapper {

    public UserDTO mapToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> mapToUserDTOList(List<User> userList) {
        return userList
                .stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    public User mapToUser(UserDTO user) {
        return modelMapper.map(user, User.class);
    }

    public User mapToUser(RegistrationDTO user) {
        return modelMapper.map(user, User.class);
    }
}

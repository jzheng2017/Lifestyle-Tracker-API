package webservice.util.mappers;

import org.springframework.stereotype.Service;
import webservice.dto.RegistrationDTO;
import webservice.dto.UserDTO;
import webservice.entities.User;

@Service
public class UserMapper extends ClassMapper {

    public UserDTO mapToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User mapToUser(UserDTO user) {
        return modelMapper.map(user, User.class);
    }

    public User mapToUser(RegistrationDTO user) {
        return modelMapper.map(user, User.class);
    }
}

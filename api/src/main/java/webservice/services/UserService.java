package webservice.services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.UserDTO;
import webservice.entities.User;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers() {
        return ((List<User>) userRepository.findAll()).stream().map(entity -> modelMapper.map(entity, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO getUser(int id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found")), UserDTO.class);
    }

    public UserDTO updateUser(UserDTO user) {
        return null;
    }

    public boolean deleteUser(int userId) {
        return false;
    }
}

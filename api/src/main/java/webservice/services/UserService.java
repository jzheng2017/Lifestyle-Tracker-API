package webservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.Registration;
import webservice.dto.UserDTO;
import webservice.entities.User;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.UserRepository;
import webservice.util.Util;

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
        User existing = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No user found"));
        Util.copyNonNullProperties(user, existing);
        return modelMapper.map(userRepository.save(existing), UserDTO.class);
    }


    public boolean deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            throw new ResourceNotFoundException("User can not be deleted. The given user does not exist.");
        }
    }

    public UserDTO addUser(Registration user) {
        return null;
    }
}

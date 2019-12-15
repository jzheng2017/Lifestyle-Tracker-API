package webservice.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.dto.CredentialDTO;
import webservice.dto.RegistrationDTO;
import webservice.dto.TokenDTO;
import webservice.dto.UserDTO;
import webservice.entities.User;
import webservice.exceptions.DuplicateEntryException;
import webservice.exceptions.ResourceNotFoundException;
import webservice.exceptions.UnauthorizedActionException;
import webservice.repositories.UserRepository;
import webservice.services.interfaces.HashService;
import webservice.util.Util;

import java.security.Key;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private HashService hashService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setHashService(HashService hashService) {
        this.hashService = hashService;
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

    public UserDTO addUser(RegistrationDTO user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateEntryException("Username already exists");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Email already exists");
        }
        user.setPassword(hashService.encode(user.getPassword())); //hash password
        return modelMapper.map(userRepository.save(modelMapper.map(user, User.class)), UserDTO.class);
    }


}

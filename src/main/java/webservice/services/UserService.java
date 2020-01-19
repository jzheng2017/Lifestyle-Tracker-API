package webservice.services;

import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webservice.dto.RegistrationDTO;
import webservice.dto.UserDTO;
import webservice.entities.User;
import webservice.exceptions.DuplicateEntryException;
import webservice.exceptions.ResourceNotFoundException;
import webservice.repositories.UserRepository;
import webservice.services.interfaces.HashService;
import webservice.util.Util;

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

    /**
     * Gets all users
     *
     * @param predicate the criteria on which the query should filter
     * @param pageable  pagination of the results
     * @return list of all users
     */
    public List<UserDTO> getAllUsers(Predicate predicate, Pageable pageable) {
        return userRepository
                .findAll(predicate, pageable)
                .stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get a specific user by id
     *
     * @param userId the id of a user
     * @return user
     */
    public UserDTO getUser(int userId) {
        return modelMapper.map(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found")), UserDTO.class);
    }


    /**
     * Update the user
     *
     * @param user user object with new values
     * @return updated user
     */
    public UserDTO updateUser(UserDTO user) {
        User existing = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No user found"));
        Util.copyNonNullProperties(user, existing);
        return modelMapper.map(userRepository.save(existing), UserDTO.class);
    }


    /**
     * Delete user by id
     *
     * @param userId the id of a user
     * @return deleted or not of type boolean
     */
    public boolean deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            throw new ResourceNotFoundException("User can not be deleted. The given user does not exist.");
        }
    }

    /**
     * Add user
     *
     * @param user registration values
     * @return object of the added user
     */
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

package webservice.repositories;


import org.springframework.data.repository.CrudRepository;
import webservice.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}

package webservice.repositories;


import org.springframework.data.repository.CrudRepository;
import webservice.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}

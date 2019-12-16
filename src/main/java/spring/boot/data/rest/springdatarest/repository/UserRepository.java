package spring.boot.data.rest.springdatarest.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.data.rest.springdatarest.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

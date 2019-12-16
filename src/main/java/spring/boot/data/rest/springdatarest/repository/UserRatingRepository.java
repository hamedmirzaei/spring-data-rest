package spring.boot.data.rest.springdatarest.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.data.rest.springdatarest.domain.UserRating;
import spring.boot.data.rest.springdatarest.domain.UserRatingPk;

import java.util.List;

public interface UserRatingRepository extends CrudRepository<UserRating, UserRatingPk> {
    List<UserRating> findByPkUserId(Long userId);
}

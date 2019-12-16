package spring.boot.data.rest.springdatarest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.boot.data.rest.springdatarest.domain.UserRating;
import spring.boot.data.rest.springdatarest.domain.UserRatingPk;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRatingRepository extends CrudRepository<UserRating, UserRatingPk> {
    List<UserRating> findByPkUserId(Long userId);
    Page<UserRating> findByPkUserId(Long userId, Pageable pageable);
}

package spring.boot.data.rest.springdatarest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import spring.boot.data.rest.springdatarest.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findByUserGroupId(@Param("id") Long id, Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends User> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends User> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(User user);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends User> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}

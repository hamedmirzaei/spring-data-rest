package spring.boot.data.rest.springdatarest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import spring.boot.data.rest.springdatarest.domain.UserGroup;

@RepositoryRestResource(collectionResourceRel = "groups", path = "groups")
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    UserGroup findByCode(@Param("code") Long code);

    @Override
    @RestResource(exported = false)
    <S extends UserGroup> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends UserGroup> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(UserGroup userGroup);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends UserGroup> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}

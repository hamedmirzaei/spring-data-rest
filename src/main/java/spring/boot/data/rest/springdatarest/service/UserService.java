package spring.boot.data.rest.springdatarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.data.rest.springdatarest.domain.User;
import spring.boot.data.rest.springdatarest.domain.UserGroup;
import spring.boot.data.rest.springdatarest.repository.UserGroupRepository;
import spring.boot.data.rest.springdatarest.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User save(String firstName, String lastName, Long userGroupCode) {
        UserGroup userGroup = userGroupRepository.findByCode(userGroupCode);
        if (userGroup == null) {
            throw new RuntimeException("userGroup does not exist with code " + userGroupCode);
        }
        return save(new User(firstName, lastName, userGroup));
    }

}

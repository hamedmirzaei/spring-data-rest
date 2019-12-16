package spring.boot.data.rest.springdatarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.data.rest.springdatarest.domain.UserGroup;
import spring.boot.data.rest.springdatarest.repository.UserGroupRepository;

@Service
public class UserGroupService {

    private UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupService(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    public UserGroup save(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

}

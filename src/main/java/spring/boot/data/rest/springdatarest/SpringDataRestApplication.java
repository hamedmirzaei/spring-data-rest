package spring.boot.data.rest.springdatarest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.boot.data.rest.springdatarest.domain.UserGroup;
import spring.boot.data.rest.springdatarest.service.UserGroupService;
import spring.boot.data.rest.springdatarest.service.UserService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SpringDataRestApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserGroupFromFile.importUserGroups().forEach(userGroupFromFile ->
                userGroupService.save(new UserGroup(userGroupFromFile.name, userGroupFromFile.code)));

        UserFromFile.importUsers().forEach(userFromFile ->
                userService.save(userFromFile.firstName, userFromFile.lastName, userFromFile.userGroupCode));
    }

    static class UserFromFile {

        private String firstName, lastName, String;
        private Long userGroupCode;

        static List<UserFromFile> importUsers() throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                    readValue(UserFromFile.class.getResourceAsStream("/users.json"),
                            new TypeReference<List<UserFromFile>>() {
                            });
        }

    }

    static class UserGroupFromFile {

        private String name;
        private Long code;

        static List<UserGroupFromFile> importUserGroups() throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                    readValue(UserGroupFromFile.class.getResourceAsStream("/usergroups.json"),
                            new TypeReference<List<UserGroupFromFile>>() {
                            });
        }

    }
}

package spring.boot.data.rest.springdatarest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.boot.data.rest.springdatarest.domain.User;
import spring.boot.data.rest.springdatarest.service.UserService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SpringDataRestApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserFromFile.importUsers().forEach(userFromFile -> userService.save(new User(userFromFile.firstName, userFromFile.lastName)));
    }

    static class UserFromFile {

        private String firstName, lastName;

        static List<UserFromFile> importUsers() throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                    readValue(UserFromFile.class.getResourceAsStream("/Users.json"),
                            new TypeReference<List<UserFromFile>>() {
                            });
        }

    }
}

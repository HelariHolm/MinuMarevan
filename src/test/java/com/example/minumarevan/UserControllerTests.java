package com.example.minumarevan;

import com.example.minumarevan.user.User;
import com.example.minumarevan.user.UserRepository;
import com.example.minumarevan.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserControllerTests {

    @Autowired
    private UserRepository repo;
    private UserService service;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setEmail("two@test.ee");
        user.setPassword("testPASSWORD123");
        user.setFirstName("John");
        user.setLastName("TestLastname");
        user.setUsername("TestUsername");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

}

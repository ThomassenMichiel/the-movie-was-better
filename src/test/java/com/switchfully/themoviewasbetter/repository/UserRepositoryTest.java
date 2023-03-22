package com.switchfully.themoviewasbetter.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.themoviewasbetter.domain.User;
import switchfully.themoviewasbetter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

class UserRepositoryTest {

    private UserRepository repository;
    private List<User> users = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        var user1 = new User("1", "123", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout");

        var user2 = new User("2", "125", "sven@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen");

        users.add(user1);
        users.add(user2);

        repository = new UserRepository(users);
    }

    @Test
    void GetAllUsersContains2Users() {
        Assertions.assertThat(repository.getAllUsers()).isEqualTo(users);
    }
}
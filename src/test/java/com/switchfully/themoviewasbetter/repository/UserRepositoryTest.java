package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserRepositoryTest {

    private MemberRepository repository;
    private List<Member> users = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        var user1 = new Member("1", "123", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");

        var user2 = new Member("2", "125", "sven@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen","passwoordTest");

        users.add(user1);
        users.add(user2);

        //repository = new MemberRepository(users);
    }

    @Test
    void GetAllUsersContains2Users() {
        Assertions.assertThat(repository.getAllUsers()).isEqualTo(users);
    }
}
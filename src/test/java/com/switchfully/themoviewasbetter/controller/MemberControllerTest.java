package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.mapper.MemberMapper;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.security.Role;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberControllerTest {
    private static final String DUMMY_AUTH_KEY = "cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206WFhY";
    private MemberController controller;

    @BeforeEach
    void setUp() {
        MemberRepository repository = new MemberRepository();
        controller = new MemberController(new MemberService(repository, new MemberMapper(), new SecurityService(repository)));
    }

    @Test
    @DisplayName("Find all")
    void findAll() {
        MemberDTO member = new MemberDTO( "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", Role.MEMBER);
        List<MemberDTO> answer = controller.findAll(DUMMY_AUTH_KEY);

        assertThat(answer).contains(member);
    }

    @Test
    @DisplayName("Find by ID")
    void findById() {
        MemberDTO member = new MemberDTO( "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", Role.MEMBER);

        MemberDTO answer = controller.findByEmail(member.getEmail());

        assertThat(answer).isEqualTo(member);
    }

    @Test
    @DisplayName("create")
    void create() {
        CreateMemberDTO member = new CreateMemberDTO("1233333", "sven33333333333@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "pw", Role.MEMBER);
        MemberDTO expected = new MemberDTO( "sven33333333333@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout",  Role.MEMBER);

        MemberDTO answer = controller.create(DUMMY_AUTH_KEY, member);

        assertThat(answer).isEqualTo(expected);
    }
}

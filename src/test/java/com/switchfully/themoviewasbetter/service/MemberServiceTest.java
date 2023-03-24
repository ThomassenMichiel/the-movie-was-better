package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.exceptions.UnauthorizedException;
import com.switchfully.themoviewasbetter.mapper.MemberMapper;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.security.Role;
import com.switchfully.themoviewasbetter.security.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Member service test")
class MemberServiceTest {
    private static final String DUMMY_AUTH_KEY = "cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206WFhY";
    private MemberMapper mapper;
    private MemberRepository repository;
    private MemberService service;
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        mapper = new MemberMapper();
        repository = new MemberRepository();
        securityService = new SecurityService(repository);
        service = new MemberService(repository, mapper, securityService);
    }

    @Test
    @DisplayName("Find all")
    void findAll() {
        Member admin = new Member("123", "pieter.pauwels13@gmail.com", "Pauwels", "",
                "", "", "", "Gent", "XXX");
        Member member = new Member("124", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");
        MemberDTO adminDto = mapper.toDto(admin);
        MemberDTO memberDTO = mapper.toDto(member);

        List<MemberDTO> answer = service.findAll(DUMMY_AUTH_KEY);

        assertThat(answer).containsExactlyInAnyOrder(adminDto, memberDTO);
    }

    @Test
    @DisplayName("Find by ID")
    void findById() {
        Member admin = new Member("123", "pieter.pauwels13@gmail.com", "Pauwels", "",
                "", "", "", "Gent", "XXX");
        MemberDTO adminDto = mapper.toDto(admin);

        MemberDTO answer = service.findByEmail(admin.getEmail());

        assertThat(answer).isEqualTo(adminDto);
    }

    @Nested
    @DisplayName("Save a member")
    class SaveMember {
        @Test
        @DisplayName("With auth key")
        void save() {
            CreateMemberDTO createMemberDto = new CreateMemberDTO("inss", "mail@mail.mail", "lastname","firstname","streetname","number","postcode","city","password", Role.MEMBER);
            MemberDTO memberDto = new MemberDTO(createMemberDto.getEmail(), createMemberDto.getLastname(), createMemberDto.getFirstname(), createMemberDto.getStreetName(), createMemberDto.getNr(), createMemberDto.getPostcode(), createMemberDto.getCity(), createMemberDto.getRole());

            MemberDTO answer = service.create(DUMMY_AUTH_KEY,createMemberDto);

            assertThat(answer).isEqualTo(memberDto);
        }

        @Test
        @DisplayName("Without auth key")
        void save_withoutAuth() {
            CreateMemberDTO createMemberDto = new CreateMemberDTO("inss", "mail@mail.mail", "lastname","firstname","streetname","number","postcode","city","password", Role.MEMBER);
            MemberDTO memberDto = new MemberDTO(createMemberDto.getEmail(), createMemberDto.getLastname(), createMemberDto.getFirstname(), createMemberDto.getStreetName(), createMemberDto.getNr(), createMemberDto.getPostcode(), createMemberDto.getCity(), createMemberDto.getRole());

            MemberDTO answer = service.create(null,createMemberDto);

            assertThat(answer).isEqualTo(memberDto);
        }

        @ParameterizedTest
        @CsvSource(textBlock = """
                ADMIN
                LIBRARIAN
                """)
        @DisplayName("Save an administrator")
        void saveAdmin(String role) {
            CreateMemberDTO createMemberDto = new CreateMemberDTO("inss", "mail@mail.mail", "lastname","firstname","streetname","number","postcode","city","password", Role.valueOf(role));
            MemberDTO memberDto = new MemberDTO(createMemberDto.getEmail(), createMemberDto.getLastname(), createMemberDto.getFirstname(), createMemberDto.getStreetName(), createMemberDto.getNr(), createMemberDto.getPostcode(), createMemberDto.getCity(), createMemberDto.getRole());

            MemberDTO answer = service.create(DUMMY_AUTH_KEY,createMemberDto);

            assertThat(answer).isEqualTo(memberDto);
        }

        @ParameterizedTest
        @CsvSource(textBlock = """
                ADMIN
                LIBRARIAN
                """)
        @DisplayName("Save an administrator without auth key")
        void saveAdmin_withoutAuth(String role) {
            CreateMemberDTO createMemberDto = new CreateMemberDTO("inss", "mail", "lastname","firstname","streetname","number","postcode","city","password", Role.valueOf(role));
            assertThatThrownBy(() -> service.create(null, createMemberDto)).isInstanceOf(UnauthorizedException.class);
        }
    }


}

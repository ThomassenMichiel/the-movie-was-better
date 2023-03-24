package com.switchfully.themoviewasbetter.security;

import com.switchfully.themoviewasbetter.exceptions.UnauthorizedException;
import com.switchfully.themoviewasbetter.exceptions.UnknownUserException;
import com.switchfully.themoviewasbetter.exceptions.WrongPasswordException;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SecurityServiceTest {
    private static final String DUMMY_AUTH_KEY = "BASIC cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206WFhY";
    private SecurityService service;

    @BeforeEach
    void setUp() {
        service = new SecurityService(new MemberRepository());
    }

    @Test
    @DisplayName("Successfull authorization")
    void validateAuthorization() {
        service.validateAuthorization(DUMMY_AUTH_KEY, Feature.REGISTER_ADMIN);
    }

    @Test
    @DisplayName("Unknown user")
    void unknownUser() {
        assertThatThrownBy(() -> service.validateAuthorization("BASIC bWljaGllbEBnbWFpbC5jb206cHc=", Feature.REGISTER_ADMIN))
                .isInstanceOf(UnknownUserException.class);
    }

    @Test
    @DisplayName("Wrong password")
    void wrongPassword() {
        assertThatThrownBy(() -> service.validateAuthorization("BASIC cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206cHdlZWVl", Feature.REGISTER_ADMIN))
                .isInstanceOf(WrongPasswordException.class);
    }

    @Test
    @DisplayName("Insufficient role")
    void insufficientRole() {
        assertThatThrownBy(() -> service.validateAuthorization("BASIC c3ZlbkBnbWFpbC5jb206cGFzc3dvb3JkVGVzdA==", Feature.REGISTER_ADMIN))
                .isInstanceOf(UnauthorizedException.class);
    }
}

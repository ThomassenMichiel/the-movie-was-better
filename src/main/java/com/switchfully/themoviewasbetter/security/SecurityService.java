package com.switchfully.themoviewasbetter.security;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.exceptions.UnauthorizedException;
import com.switchfully.themoviewasbetter.exceptions.UnknownUserException;
import com.switchfully.themoviewasbetter.exceptions.WrongPasswordException;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberRepository memberRepository;

    public SecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        Credentials emailPassword = getCredentials(authorization);
        Member user = memberRepository.findById(emailPassword.getEmail());

        if (user == null) {
            logger.error("Unknown user {}", emailPassword.getEmail());
            throw new UnknownUserException();
        }
        if (!user.doesPasswordMatch(emailPassword.getPassword())) {
            logger.error("Password does not match for user {}", emailPassword.getEmail());
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error("User {} does not have access to {}", emailPassword.getEmail(), feature);
            throw new UnauthorizedException();
        }
    }

    private Credentials getCredentials(String authorization) {
        try {
            String decodedUsernameAndPassword = new String(Base64.getDecoder()
                    .decode(authorization.substring("".length())));
            logger.error("decodedUsernameAndPassword: {}", decodedUsernameAndPassword);
            String username = decodedUsernameAndPassword
                    .substring(0, decodedUsernameAndPassword.indexOf(":"));
            logger.error("username: {}", username);
            String password = decodedUsernameAndPassword
                    .substring(decodedUsernameAndPassword.indexOf(":") + 1);
            logger.error("password: {}", password);
            return new Credentials(username, password);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UnauthorizedException();
        }

    }
}

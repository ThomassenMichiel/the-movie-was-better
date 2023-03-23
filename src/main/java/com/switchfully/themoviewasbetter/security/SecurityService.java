package com.switchfully.themoviewasbetter.security;

import com.switchfully.themoviewasbetter.exceptions.UnauthorizedException;
import com.switchfully.themoviewasbetter.exceptions.UnknownUserException;
import com.switchfully.themoviewasbetter.exceptions.WrongPasswordException;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.switchfully.themoviewasbetter.domain.Member;

import java.util.Base64;

import static java.lang.String.format;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final MemberRepository memberRepository;

    public SecurityService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        Credentials emailPassword = getUsernamePassword(authorization);
        Member user = memberRepository.getMember(emailPassword.getEmail());

        if (user == null) {
            logger.error(format("Unknown user %s", emailPassword.getEmail()));
            throw new UnknownUserException();
        }
        if (!user.doesPasswordMatch(emailPassword.getPassword())) {
            logger.error(format("Password does not match for user %s", emailPassword.getEmail()));
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error(format("User %s does not have access to %s", emailPassword.getEmail(), feature));
            throw new UnauthorizedException();
        }
    }
    
    private Credentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder()
                .decode(authorization.substring("".length())));
        logger.error("decodedUsernameAndPassword: " + decodedUsernameAndPassword);
        String username = decodedUsernameAndPassword
                .substring(0, decodedUsernameAndPassword.indexOf(":"));
        logger.error("username: " + username);
        String password = decodedUsernameAndPassword
                .substring(decodedUsernameAndPassword.indexOf(":") + 1);
        logger.error("password: " + password);
        return new Credentials(username, password);
    }
}

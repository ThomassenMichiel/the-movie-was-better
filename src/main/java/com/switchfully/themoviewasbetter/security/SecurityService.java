package com.switchfully.themoviewasbetter.security;

import com.switchfully.themoviewasbetter.exceptions.UnauthorizatedException;
import com.switchfully.themoviewasbetter.exceptions.UnknownUserException;
import com.switchfully.themoviewasbetter.exceptions.WrongPasswordException;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import switchfully.themoviewasbetter.domain.Member;
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
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        Member user = memberRepository.getMember(usernamePassword.getUserId());

        if (user == null) {
            logger.error(format("Unknown user %s", usernamePassword.getUserId()));
            throw new UnknownUserException();
        }
        if (!user.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error(format("Password does not match for user %s", usernamePassword.getUserId()));
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error(format("User %s does not have access to %s", usernamePassword.getUserId(), feature));
            throw new UnauthorizatedException();
        }

    }

    private UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder()
                .decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword
                .substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword
                .substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }
}

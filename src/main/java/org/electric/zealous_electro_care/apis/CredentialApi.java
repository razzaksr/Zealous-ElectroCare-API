package org.electric.zealous_electro_care.apis;

import org.electric.zealous_electro_care.configs.JwtUtility;
import org.electric.zealous_electro_care.entities.User;
import org.electric.zealous_electro_care.middles.UserMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class CredentialApi {

    @Autowired
    private UserMiddle middle;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtility utility;
    private Logger logger = LoggerFactory.getLogger(CredentialApi.class);
    
    // endpoints
    @PostMapping("/signup")
    public User signUp(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Signing up with encoded password");
        return middle.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        logger.info("Authentication created");
        if(authentication.isAuthenticated()){
            logger.info("Authentication verified");
            // generqate token using jwt
            return utility.generateToken(user.getUsername());
        }
        else{
            throw new RuntimeException("Invalid access");
        }
    }
}

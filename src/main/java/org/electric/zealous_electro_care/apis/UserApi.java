package org.electric.zealous_electro_care.apis;

import java.util.List;

import org.electric.zealous_electro_care.entities.User;
import org.electric.zealous_electro_care.middles.UserMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    @Autowired
    private UserMiddle middle;
    private Logger logger = LoggerFactory.getLogger(UserApi.class);

    // endpoints
    @GetMapping("/")
    public List<User> readlAll(){
        logger.info("reading all user");
        return middle.getAll();
    }
    @PostMapping("/")
    public User signup(@RequestBody User user){
        logger.info("signup new user");
        return middle.createUser(user);
    }
    @GetMapping("/role/{role}")
    public List<User> readViaUSers(@PathVariable String role){
        logger.info("read users of role "+role);
        return middle.getByRole(role);
    }
    @PutMapping("/")
    public User change(@RequestBody User user){
        return middle.createUser(user);
    }
}

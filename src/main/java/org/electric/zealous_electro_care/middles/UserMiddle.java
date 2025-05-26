package org.electric.zealous_electro_care.middles;

import java.util.List;

import org.electric.zealous_electro_care.entities.User;
import org.electric.zealous_electro_care.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserMiddle implements UserDetailsService{
    @Autowired
    private UserRepo repo;

    public List<User> getAll(){
        return repo.findAll();
    }

    public List<User> getByRole(String role){
        return repo.findAllByRole(role);
    }

    public User createUser(User user){
        return repo.save(user);
    }

    public User getUserByUsername(String username){
        return repo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = getUserByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException(username);
        return user;
    }
    
}

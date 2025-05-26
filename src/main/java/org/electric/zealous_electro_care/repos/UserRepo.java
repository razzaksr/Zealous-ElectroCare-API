package org.electric.zealous_electro_care.repos;

import java.util.List;

import org.electric.zealous_electro_care.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
    User findByUsername(String username);
    List<User> findAllByRole(String role);
}

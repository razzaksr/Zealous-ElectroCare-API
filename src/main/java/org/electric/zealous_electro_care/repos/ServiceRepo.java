package org.electric.zealous_electro_care.repos;

import org.electric.zealous_electro_care.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service,Integer>{
    
}

package org.electric.zealous_electro_care.repos;

import java.util.List;

import org.electric.zealous_electro_care.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepo extends JpaRepository<Technician,String> {
    Technician findByUsername(String username);
    @Query("SELECT p FROM Technician p WHERE p.available = true ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(p.latitude)))) ASC")
    List<Technician> findNearbyTechnicians(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}

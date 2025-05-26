package org.electric.zealous_electro_care.repos;

import java.util.List;

import org.electric.zealous_electro_care.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findAllByStatus(String status);
    List<Booking> findAllByTechnician(String technician);
    List<Booking> findAllByBookedBy(String username);
}

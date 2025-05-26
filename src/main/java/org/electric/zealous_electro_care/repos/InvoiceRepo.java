package org.electric.zealous_electro_care.repos;

import java.util.List;

import org.electric.zealous_electro_care.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Long>{
    @Query("Select bookingId from Invoice")
    List<Long> findBookingIds();
    Invoice findAllByBookingId(long id);
}

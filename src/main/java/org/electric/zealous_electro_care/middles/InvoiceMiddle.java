package org.electric.zealous_electro_care.middles;

import java.util.List;

import org.electric.zealous_electro_care.entities.Invoice;
import org.electric.zealous_electro_care.repos.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMiddle {
    @Autowired
    private InvoiceRepo repo;

    public Invoice getOne(long id){
        return repo.findAllByBookingId(id);
    }
    
    public List<Invoice> viewInvoices(){
        return repo.findAll();
    }

    public List<Long> viewExistsBooks(){
        return repo.findBookingIds();
    }

    public Invoice saveInvoice(Invoice invoice) {
        return repo.save(invoice);
    }
}

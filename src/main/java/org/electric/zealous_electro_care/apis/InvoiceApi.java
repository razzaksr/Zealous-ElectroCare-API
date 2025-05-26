package org.electric.zealous_electro_care.apis;

import java.util.List;

import org.electric.zealous_electro_care.entities.Invoice;
import org.electric.zealous_electro_care.middles.InvoiceMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi {
    @Autowired
    private InvoiceMiddle middle;
    private Logger logger = LoggerFactory.getLogger(InvoiceApi.class);
    //endpoints
    @GetMapping("/")
    public List<Invoice> readAll(){
        logger.info("Viewing all invoices");
        return middle.viewInvoices();
    }
    @GetMapping("/{id}")
    public Invoice getByBookingId(@PathVariable long id){
        return middle.getOne(id);
    }
}

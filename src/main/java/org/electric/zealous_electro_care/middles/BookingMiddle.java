package org.electric.zealous_electro_care.middles;

import java.time.LocalDateTime;
import java.util.List;

import org.electric.zealous_electro_care.entities.Booking;
import org.electric.zealous_electro_care.entities.Invoice;
import org.electric.zealous_electro_care.entities.Request;
import org.electric.zealous_electro_care.entities.Technician;
import org.electric.zealous_electro_care.entities.User;
import org.electric.zealous_electro_care.repos.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingMiddle {
    @Autowired
    private BookingRepo repo;
    @Autowired
    private UserMiddle middle;
    @Autowired
    private ServiceMiddle serviceMiddle;
    @Autowired
    private InvoiceMiddle invoiceMiddle;
    @Autowired
    private TechnicianMiddle technicianMiddle;

    public Booking getOneById(long id){
        return repo.findById(id).orElse(null);
    }

    public List<Booking> viewUpComing(){
        return repo.findAllByStatus("CONFIRMED");
    }

    public List<Booking> viewByTechnician(String username){
        return repo.findAllByTechnician(username);
    }

    public Booking updateBooking(Booking booking){
        if(booking.getStatus().equals("DONE")){
            if(!invoiceMiddle.viewExistsBooks().contains(booking.getBookingId())){
                Invoice invoice = new Invoice();
                invoice.setBookingId(booking.getBookingId());
                double amount = serviceMiddle.getServiceById(booking.getService()).getPrice();
                amount += (amount*0.180);
                invoice.setInvoiceAmount(amount);
                invoice.setInvoiceDate(LocalDateTime.now());
                invoiceMiddle.saveInvoice(invoice);
            }
        }
        return repo.save(booking);
    }

    public List<Booking> viewBooks(){
        return repo.findAll();
    }

    public List<Booking> viewByUsers(String username){
        return repo.findAllByBookedBy(username);
    }

    public Booking newBooking(Request request){
        LocalDateTime schedule = LocalDateTime.now();
        User user = middle.getUserByUsername(request.getBookingBy());
        org.electric.zealous_electro_care.entities.Service service = serviceMiddle.getServiceById(request.getService());
        Technician scheduledTecnician = technicianMiddle.nearTechnicians(request.getLatitude(), request.getLongitude()).get(0);
        if(user.getRole().equals("customer")&&service!=null){
            if(scheduledTecnician==null){
                throw new RuntimeException("No available technicians nearby.");
            }
            Booking booking = new Booking();
            booking.setBookedBy(request.getBookingBy());
            booking.setScheduledDateTime(schedule);
            booking.setStatus("Booking");
            booking.setService(request.getService());
            booking.setTechnician(scheduledTecnician.getUsername());
            booking.setStatus("CONFIRMED");
            repo.save(booking);
            return booking;
        }
        return null;
    }
}

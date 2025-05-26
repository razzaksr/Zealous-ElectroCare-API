package org.electric.zealous_electro_care.apis;

import java.util.List;

import org.electric.zealous_electro_care.entities.Booking;
import org.electric.zealous_electro_care.entities.Invoice;
import org.electric.zealous_electro_care.entities.Request;
import org.electric.zealous_electro_care.entities.Response;
import org.electric.zealous_electro_care.entities.User;
import org.electric.zealous_electro_care.middles.BookingMiddle;
import org.electric.zealous_electro_care.middles.InvoiceMiddle;
import org.electric.zealous_electro_care.middles.UserMiddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingApi {
    @Autowired
    private BookingMiddle middle;
    @Autowired
    private UserMiddle userMiddle;
    @Autowired
    private InvoiceMiddle invoiceMiddle;

    // endpoints

    @GetMapping("/{id}")
    public Booking getOne(@PathVariable long id){
        return middle.getOneById(id);
    }

    @PutMapping("/update")
    public Invoice updateBook(@RequestBody Booking booking){
        if(middle.updateBooking(booking)!=null){
            return invoiceMiddle.getOne(booking.getBookingId());
        }
        return null;
    }

    @GetMapping("/")
    public List<Booking> fetchBookings(){
        return middle.viewBooks();
    }

    @GetMapping("/by/{user}")
    public List<Booking> viewBookings(@PathVariable String user){
        return middle.viewByUsers(user);
    }

    @PostMapping("/conform")
    public ResponseEntity conformBooking(@RequestBody Request request) {
        try{
            Booking booking = middle.newBooking(request);
            if(booking!=null){
                User user = userMiddle.getUserByUsername(booking.getTechnician());
                Response response = new Response();
                response.setBookedBy(booking.getBookedBy());
                response.setBookingId(booking.getBookingId());
                response.setFullName(user.getFullName());
                response.setMobile(user.getMobile());
                response.setScheduledDateTime(booking.getScheduledDateTime());
                response.setService(booking.getService());
                response.setTechnician(booking.getTechnician());
                response.setStatus(booking.getStatus());
                return ResponseEntity.ok(response);
            }
            else
                return ResponseEntity.ofNullable("Booking Not confirmed");
        }
        catch(RuntimeException runtimeException){
            return ResponseEntity.ofNullable("Technician/ Service was not available ");
        }
    }

    // @PostMapping("/conform/{username}/{serviceId}")
    // public ResponseEntity<String> conformBooking(@PathVariable String username,@PathVariable int serviceId) {
    //     LocalDateTime schedule = LocalDateTime.now();
    //     try{
    //         if(middle.newBooking(username, schedule, serviceId)!=null)
    //             return ResponseEntity.ok("Booking confirmed & notification sent!");
    //         else
    //             return ResponseEntity.ofNullable("Booking Not confirmed");
    //     }
    //     catch(RuntimeException runtimeException){
    //         return ResponseEntity.ofNullable("Technician/ Service was not available ");
    //     }
    // }

    // @PostMapping("/confirm/{username}/{schedule}/{serviceId}")
    // public ResponseEntity<String> confirmBooking(@PathVariable String username,@PathVariable LocalDateTime schedule,@PathVariable int serviceId) {
    //     try{
    //         if(middle.newBooking(username, schedule, serviceId)!=null)
    //             return ResponseEntity.ok("Booking confirmed & notification sent!");
    //         else
    //             return ResponseEntity.ofNullable("Booking Not confirmed");
    //     }
    //     catch(RuntimeException runtimeException){
    //         return ResponseEntity.ofNullable("Technician/ Service was not available ");
    //     }
    // }
}

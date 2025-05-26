package org.electric.zealous_electro_care.apis;

import java.util.List;

import org.electric.zealous_electro_care.entities.Technician;
import org.electric.zealous_electro_care.middles.TechnicianMiddle;
import org.electric.zealous_electro_care.middles.UserMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/technicians")
public class TechnicianApi {
    @Autowired
    private TechnicianMiddle middle;
    @Autowired
    private UserMiddle userMiddle;
    private Logger logger = LoggerFactory.getLogger(TechnicianApi.class);

    @GetMapping("/{username}")
    public Technician getTechnician(@PathVariable String username){
        return middle.getTechnicianByUsername(username);
    }

    @PutMapping("/rate/{username}/{rate}")
    public Technician rates(@PathVariable String username, @PathVariable double rate){
        return middle.feedbackTechnician(username, rate);
    }

    @PutMapping("/status/{username}/{status}")
    public Technician avail(@PathVariable String username, @PathVariable boolean status){
        return middle.updating(username, status);
    }

    @GetMapping("/")
    public List<Technician> readAll(){
        return middle.viewAll();
    }

    @PostMapping("/")
    public ResponseEntity<String> signup(@RequestBody Technician user){
        if(userMiddle.getUserByUsername(user.getUsername())!=null){
            logger.info("signup new technician");
            middle.createTechnician(user);
            return ResponseEntity.ok("Technician added");
        }
        else{
            return ResponseEntity.ofNullable("Technician was not available");
        }
    }

    @GetMapping("/near/{lat}/{lon}")
    public List<Technician> readViaLocation(@PathVariable double lat,@PathVariable double lon){
        return middle.nearTechnicians(lat, lon);
    }
}

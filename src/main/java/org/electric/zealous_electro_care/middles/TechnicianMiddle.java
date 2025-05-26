package org.electric.zealous_electro_care.middles;

import java.util.List;

import org.electric.zealous_electro_care.entities.Technician;
import org.electric.zealous_electro_care.repos.TechnicianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicianMiddle {
    @Autowired
    private TechnicianRepo repo;

    public Technician feedbackTechnician(String username, double current){
        Technician technician = getTechnicianByUsername(username);
        technician.setRating(technician.getRating()+current);
        technician.setRatingCount(technician.getRatingCount()+1);
        technician.setAvgRating(technician.getRating()/technician.getRatingCount());
        return repo.save(technician);
    }

    public Technician updating(String username, boolean status){
        Technician technician = getTechnicianByUsername(username);
        technician.setAvailable(status);
        return repo.save(technician);
    }

    public Technician createTechnician(Technician technician){
        return repo.save(technician);
    }

    public Technician getTechnicianByUsername(String username){
        return repo.findByUsername(username);
    }

    public List<Technician> viewAll(){
        return repo.findAll();
    }

    public List<Technician> nearTechnicians(double latitude,double longitude){
        return repo.findNearbyTechnicians(latitude,longitude);
    }
}

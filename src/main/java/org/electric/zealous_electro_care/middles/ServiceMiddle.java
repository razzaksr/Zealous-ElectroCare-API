package org.electric.zealous_electro_care.middles;

import java.util.List;

import org.electric.zealous_electro_care.entities.Service;
import org.electric.zealous_electro_care.repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceMiddle {
    @Autowired
    private ServiceRepo repo;
    public Service newService(Service service){
        return repo.save(service);
    }
    public Service getServiceById(int id){
        return repo.findById(id).orElse(null);
    }
    public List<Service> getAllServices(){
        return repo.findAll();
    }
    public void removeServiceById(int id){
        repo.deleteById(id);
    }
}

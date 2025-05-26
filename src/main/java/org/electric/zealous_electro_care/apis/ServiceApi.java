package org.electric.zealous_electro_care.apis;

import java.util.List;

import org.electric.zealous_electro_care.entities.Service;
import org.electric.zealous_electro_care.middles.ServiceMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceApi {
    @Autowired
    private ServiceMiddle middle;
    private Logger logger = LoggerFactory.getLogger(ServiceApi.class);

    // endpoints
    @GetMapping("/")
    public List<Service> fetchServices(){
        logger.info("Listing all services");
        return middle.getAllServices();
    }
    @PostMapping("/")
    public Service addService(@RequestBody Service service){
        logger.info("new service "+service.getServiceName()+" has added");
        return middle.newService(service);
    }
    @PutMapping("/")
    public Service updatService(@RequestBody Service service){
        logger.info("service "+service.getServiceName()+" has updated");
        return middle.newService(service);
    }
    @GetMapping("/{id}")
    public Service fetchOptional(@PathVariable int id){
        logger.info("service "+id+" has been read");
        return middle.getServiceById(id);
    }
    @DeleteMapping("/{id}")
    public void eraseServiceById(@PathVariable int id){
        logger.info("service "+id+" has been removed");
        middle.removeServiceById(id);
    }
}

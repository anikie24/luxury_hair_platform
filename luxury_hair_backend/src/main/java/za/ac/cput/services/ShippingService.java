package za.ac.cput.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Shipping;
import za.ac.cput.repository.ShippingRepository;

import java.util.List;

@Service
public class ShippingService implements IShippingService{

    @Autowired
    private ShippingRepository repository;

    @Autowired
    private AddressService addressService;
    ShippingService(ShippingRepository repo){this.repository= repo;}


    @Override
    public Shipping create(Shipping shipping){
        if (shipping.getAddress() != null) {
            addressService.create(shipping.getAddress());
        }
        
        return repository.save(shipping);
    }
    @Override
    public Shipping read (String shippingID){return repository.findById(shippingID).orElse(null);
    }
    @Override
    public Shipping update (Shipping shipping){return repository.save(shipping);
    }
    @Override
    public List<Shipping> getall(){return repository.findAll();}

}

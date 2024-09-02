package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Shipping;
import za.ac.cput.services.ShippingService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/shipping")
public class ShippingController {

        @Autowired
        private ShippingService shippingService;

        @PostMapping("/create")
        public ResponseEntity<Shipping> create(@RequestBody Shipping shipping) {
            Shipping createdInfo = shippingService.create(shipping);
            return new ResponseEntity<>(createdInfo, HttpStatus.CREATED);
        }
        
    

        @GetMapping("/read/{shippingId}")
        private Shipping read(@PathVariable String shippingId){
            return shippingService.read(shippingId);
        }

        @PostMapping("/update")
        private Shipping update(@RequestBody Shipping shipping){
            return shippingService.update(shipping);
        }

        @GetMapping("/getall")
        private List<Shipping>getall(){
            return shippingService.getall();
        }
    }


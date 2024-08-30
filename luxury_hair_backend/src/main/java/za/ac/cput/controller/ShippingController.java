package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Shipping;
import za.ac.cput.services.ShippingService;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

        @Autowired
        private ShippingService shippingService;

        @PostMapping("/create")
        public Shipping create(@RequestBody Shipping shipping){
            return shippingService.create(shipping);
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


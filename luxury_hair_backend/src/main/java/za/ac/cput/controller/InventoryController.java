package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.services.InventoryService;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/inventory")

public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create")
    public Inventory create(@RequestBody Inventory inv){
        return inventoryService.create(inv);
    }

    @GetMapping("/read/{invId}")
    private Inventory read(@PathVariable String invId){
        return inventoryService.read(invId);
    }

    @PostMapping("/update")
    private Inventory update(@RequestBody Inventory inv){
        return inventoryService.update(inv);
    }

    @GetMapping("/getall")
    private List<Inventory>getall(){
        return inventoryService.getall();
    }
}

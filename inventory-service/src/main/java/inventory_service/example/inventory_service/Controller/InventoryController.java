package inventory_service.example.inventory_service.Controller;


import inventory_service.example.inventory_service.entity.Inventory;
import inventory_service.example.inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(
            InventoryService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public Inventory addStock(
            @RequestBody Inventory inventory) {

        return service.addStock(inventory);
    }

    @GetMapping("/{productId}")
    public Inventory getStock(
            @PathVariable Long productId) {

        return service.getStock(productId);
    }

    @PutMapping("/reduce/{productId}")
    public String reduceStock(
            @PathVariable Long productId) {

        return service.reduceStock(productId);
    }

    @GetMapping("/count")
    public Long getInventoryCount() {
        return service.getInventoryCount();
    }

   /* @GetMapping("/{id}")
    public String checkInventory(
            @PathVariable Long id)
    {
        return "Product Available";
    } */
}

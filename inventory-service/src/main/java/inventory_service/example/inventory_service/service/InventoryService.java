package inventory_service.example.inventory_service.service;

import inventory_service.example.inventory_service.entity.Inventory;
import inventory_service.example.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(
            InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory addStock(
            Inventory inventory) {

        return repository.save(inventory);
    }

    public Inventory getStock(
            Long productId) {

        return repository.findById(productId)
                .orElse(null);
    }

    public String reduceStock(
            Long productId) {
        Inventory inventory =
                repository.findById(productId)
                        .orElseThrow();

        if (inventory.getQuantity() <= 0) {
            return "Out Of Stock";
        }

        inventory.setQuantity(
                inventory.getQuantity() - 1);

        repository.save(inventory);

        return "Stock Updated";
    }
    public Long getInventoryCount() {
        return repository.count();
    }

}

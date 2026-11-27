package order_service.example.order_service.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryClient {

    @Autowired
    private RestTemplate restTemplate;

    public String checkInventory(Long id) {

        return restTemplate.getForObject(
                "http://INVENTORY-SERVICE/inventory/" + id,
                String.class
        );
    }
}


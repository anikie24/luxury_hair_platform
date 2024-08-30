package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Inventory;
import za.ac.cput.factory.InventoryFactory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


class InventoryControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/inventory";

    private static Inventory inv;

    @BeforeEach
    @Order(1)
    public void setUp() {
        inv = InventoryFactory.buildInventory("inv111", "prod111", 50, "10 Dorset street", "sup23", 8000);

    }

    @Test
    @Order(2)
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Inventory> postResponse = restTemplate.postForEntity(url, inv, Inventory.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Inventory inventorySaved = postResponse.getBody();
        assertEquals(inv.getInventoryID(), inventorySaved.getInventoryID());
        System.out.println("Saved data: " + inventorySaved);
    }

    @Test
    @Order(3)
    void read() {
        String inventoryID = "Inv322";
        String url = BASE_URL + "/getall" + inventoryID;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Inventory> response = restTemplate.exchange(url, HttpMethod.GET, entity, Inventory.class);

        System.out.println("Show ALL inventory: " + inventoryID);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void update() {
        String url = BASE_URL + "/update";
        Inventory newInventory = new Inventory.Builder().copy(inv).setQuantityAv(100).build();
        ResponseEntity<Inventory> postResponse = restTemplate.postForEntity(url, newInventory, Inventory.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Inventory updated = postResponse.getBody();
        assertEquals(newInventory.getInventoryID(), updated.getInventoryID());
        System.out.println("Updated data: " + updated);
    }

    @Test
    @Order(5)
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("Show all");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}


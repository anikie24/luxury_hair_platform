package za.ac.cput.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Shipping;
import za.ac.cput.factory.ShippingFactory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class ShippingControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/shipping";

    private static Shipping shipping;


    @BeforeEach
    @Order(1)
    public void setUp() {
        shipping = ShippingFactory.buildShipping("shipment101", "prod322", "Aramex", 1224,100.00,"25/06/2024", "29/06/2024", "Pending");
    }

    @Test
    @Order(2)
     public void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Shipping> postResponse = restTemplate.postForEntity(url, shipping, Shipping.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());


        Shipping shippingSaved = postResponse.getBody();
        assertEquals(shipping.getShippingID(), shippingSaved.getShippingID());
        System.out.println("Saved data: " + shippingSaved);
    }


    @Test
    @Order(3)
    void read() {
        String shippingID = "Sh9922";
        String url = BASE_URL + "/getall" +shippingID;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<Shipping> response = restTemplate.exchange(url, HttpMethod.GET, entity, Shipping.class);

        System.out.println("Show ALL products : " + shippingID);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void update() {
        String url = BASE_URL + "/update";
        Shipping newShipping = new Shipping.Builder().copy(shipping).setDeliveryStatus("Delivered").build();
        ResponseEntity<Shipping> postResponse = restTemplate.postForEntity(url, newShipping, Shipping.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Shipping updated = postResponse.getBody();
        assertEquals(newShipping.getShippingID(), updated.getShippingID());
        System.out.println("Updated data: " + updated);
    }

    @Test
    @Order(5)
    void getall() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("Show all");
        System.out.println(response);
        System.out.println(response.getBody());
    }

}

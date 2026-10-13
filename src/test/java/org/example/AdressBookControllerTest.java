package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class AdressBookControllerTest {

    @Autowired
    private RestTestClient client;

    @Test
    void createAddressBook()
    {
            client.post()
                .uri("/api/addressbooks")
                .contentType(MediaType.APPLICATION_JSON)
                .body("{}")
                .exchange().expectStatus().isOk();
    }

    @Test
    void createBuddyInfo(){
        String buddyJohn = """
                {
                    "name": "John",
                    "phoneNumber": "12345678"
                }
                """;
        client.post().uri("/api/buddies").contentType(MediaType.APPLICATION_JSON)
                .body(buddyJohn).exchange().expectStatus().isOk();
    }

    @Test
    void addBuddyToAddressBook()
    {
        String buddyUrl = "/api/buddies";
        String addressBookUrl = "/api/addressbooks";
        String buddy = """
                {
                    "name": "Iheanyi",
                    "phoneNumber": "23456789"
                }
                """;
        String buddyRes = client.post().uri(buddyUrl).contentType(MediaType.APPLICATION_JSON)
                .body(buddy).exchange().expectStatus()
                .isOk().expectBody(String.class).returnResult().getResponseBody();

        String addressBookRes = client.post().uri(addressBookUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{}").exchange().expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();

        JsonNode buddyNode = new ObjectMapper().readTree(buddyRes);
        int buddyId = buddyNode.get("id").asInt();
        JsonNode addressBookNode = new ObjectMapper().readTree(addressBookRes);
        int addressBookId = addressBookNode.get("id").asInt();

        client.post()
                .uri(addressBookUrl+"/"+addressBookId+"/buddies/"+buddyId).exchange().expectStatus().isOk();

        client.get().uri(addressBookUrl+"/"+addressBookId)
                .exchange().expectStatus().isOk().expectBody()
                .jsonPath("$.buddies[0].name").isEqualTo("Iheanyi")
                .jsonPath("$.buddies[0].phoneNumber").isEqualTo("23456789");
    }
}

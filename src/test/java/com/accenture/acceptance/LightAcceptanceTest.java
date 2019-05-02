package com.accenture.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LightAcceptanceTest {

    @Autowired
    private WebTestClient webClient;

    private static final String ON_IMAGE_URL = "https://www.roohanrealty.com/wp-content/uploads/2017/02/light-bulb-768x768.png";
    private static final String OFF_IMAGE_URL = "http://www.clker.com/cliparts/c/c/7/5/1194989703548327975lightbulb_jon_phillips_04.svg.med.png";

    @Test
    public void GivenTheLightIsOff_WhenTurnTheLightOn_ThenTheLightTurnsOn(){
        String body = webClient.get().uri("/on").exchange().expectStatus().isOk().expectBody(String.class)
                .returnResult().getResponseBody();

        assertTrue(body.contains("<img src=\"" + ON_IMAGE_URL + "\"/>"));
    }

    // When I send a get request for off the the light turns off
}

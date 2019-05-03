package com.accenture.acceptance;

import com.accenture.IotImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LightAcceptanceTest implements IotImage {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void GivenTheLightIsOff_WhenTurnTheLightOn_ThenTheLightTurnsOn(){
        givenTheLightIsOff();

        turnLightOn();

        verifyLightStatusChanged(ON_IMAGE_URL);
    }

    @Test
    public void GivenTheLightIsOn_WhenTurnTheLightOff_ThenTheLightTurnsOff(){
        givenTheLightIsOn();

        turnLightOff();

        verifyLightStatusChanged(OFF_IMAGE_URL);
    }

    private void givenTheLightIsOn() {
        turnLightOn();

        verifyLightStatusChanged(ON_IMAGE_URL);
    }

    private void givenTheLightIsOff() {
        turnLightOff();

        verifyLightStatusChanged(OFF_IMAGE_URL);
    }

    private void turnLightOff() {
        webClient.get().uri("/turn/off").exchange().expectStatus().isOk();
    }

    private void turnLightOn() {
        webClient.get().uri("/turn/on").exchange().expectStatus().isOk();
    }

    private void verifyLightStatusChanged(String statusImageUrl) {
        String body = webClient.get().uri("/home").exchange().expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        assertTrue(body.contains("src=\"" + statusImageUrl + "\""));
    }
}

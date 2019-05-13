package com.accenture;

import com.accenture.mqtt.LightSwitchSubscriber;
import org.apache.activemq.broker.BrokerService;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.BeforeClass;
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

    private static MqttClient publisher;
    private final static String TOPIC = "iot.light.switch";
    private final static String MESSAGE_TURN_ON = "on";
    private final static String BROKER_URL = "://localhost:1883";

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private LightSwitchSubscriber switchSubscriber;

    @BeforeClass
    public static void setup() throws Exception {
        BrokerService activeMQBroker = new BrokerService();
        activeMQBroker.addConnector("mqtt" + BROKER_URL);
        activeMQBroker.setPersistent(false);
        activeMQBroker.setUseJmx(false);
        activeMQBroker.start();

        publisher = new MqttClient("tcp" + BROKER_URL, MqttAsyncClient.generateClientId());
        publisher.connect();
    }

    @Test
    public void GivenTheLightIsOff_WhenTurnTheLightOn_ThenTheLightTurnsOn_ViaMqtt() throws Exception {
        givenTheLightIsOff();

        switchSubscriber.subscribe();

        publisher.publish(TOPIC, new MqttMessage(MESSAGE_TURN_ON.getBytes()));

        Thread.sleep(500);

        verifyLightStatusChanged(ON_IMAGE_URL);
    }

    @Test
    public void GivenTheLightIsOn_WhenTurnTheLightOff_ThenTheLightTurnsOff_ViaHttp(){
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

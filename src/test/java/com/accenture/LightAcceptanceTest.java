package com.accenture;

import com.accenture.mqtt.LightPublisher;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LightAcceptanceTest implements IotImage {

    private final static String BROKER_URL = "://localhost:1883";

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private LightSwitchSubscriber switchSubscriber;

    @Autowired
    private LightPublisher lightPublisher;

    @BeforeClass
    public static void setup() throws Exception {
        BrokerService activeMQBroker = new BrokerService();
        activeMQBroker.addConnector("mqtt" + BROKER_URL);
        activeMQBroker.setPersistent(false);
        activeMQBroker.setUseJmx(false);
        activeMQBroker.start();

        MqttClient publisher = new MqttClient("tcp" + BROKER_URL, MqttAsyncClient.generateClientId());
        publisher.connect();
    }

    @Test
    public void GivenTheLightIsOff_WhenLightIsToggled_ThenTheLightTurnsOn_ViaMqtt() throws Exception {
        givenTheLightIsOff();

        switchSubscriber.subscribe();

        lightPublisher.publish();

        Thread.sleep(500);

        verifyLightStatusChanged(ON_IMAGE_URL);
    }

    @Test
    public void GivenTheLightIsOn_WhenLightIsToggled_ThenTheLightTurnsOff_ViaHttp() throws Exception {
        givenTheLightIsOn();

        switchSubscriber.subscribe();

        toggleLight();

        Thread.sleep(500);

        verifyLightStatusChanged(OFF_IMAGE_URL);
    }

    private void givenTheLightIsOn() {
        if (!verifyLightStatusChanged(ON_IMAGE_URL)) {
            toggleLight();
        }
    }

    private void givenTheLightIsOff() {
        if (!verifyLightStatusChanged(OFF_IMAGE_URL)) {
            toggleLight();
        }
    }

    private void toggleLight() {
        webClient.get().uri("/toggle").exchange().expectStatus().isOk();
    }

    private boolean verifyLightStatusChanged(String statusImageUrl) {
        String body = webClient.get().uri("/home").exchange().expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        return body.contains("src=\"" + statusImageUrl + "\"");
    }
}

package com.accenture.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MqttClientFactoryTest {

    private static final String BROKER_URL = "tcp://testurl";

    @Test
    public void create_ThenReturnInstanceOfTheCorrectType(){
        MqttClientFactory mqttClientFactory = new MqttClientFactory();

        MqttClient mqttClient = mqttClientFactory.create(BROKER_URL);

        assertNotNull(mqttClient);
    }

    @Test
    public void create_ThenReturnClientWithCorrectUrl(){
        MqttClientFactory mqttClientFactory = new MqttClientFactory();

        MqttClient mqttClient = mqttClientFactory.create(BROKER_URL);

        assertEquals(BROKER_URL, mqttClient.getServerURI());
    }
}

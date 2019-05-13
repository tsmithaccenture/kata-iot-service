package com.accenture.mqtt;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Component
public class MqttClientFactory {

    public MqttClient create(String brokerUrl) {
        try {
            return new MqttClient(brokerUrl, MqttAsyncClient.generateClientId());
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}

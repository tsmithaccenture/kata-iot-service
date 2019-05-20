package com.accenture.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LightPublisher {

    @Resource
    private MqttClientFactory mqttClientFactory;

    public void publish(){
        try {
            MqttClient publisher = mqttClientFactory.create(IotMqtt.BROKER_URL);

            publisher.connect();

            publisher.publish(IotMqtt.TOPIC, new MqttMessage(IotMqtt.MESSAGE.getBytes()));
        } catch (MqttException e) {
            new RuntimeException(e);
        }
    }
}

package com.accenture.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Component
public class LightSwitchSubscriber {

    public final static String TOPIC = "iot.light.switch";
    public final static String BROKER_URL = "tcp://localhost:1883";
    private LightCallback lightCallback;
    private MqttClientFactory mqttClientFactory;

    public LightSwitchSubscriber(LightCallback lightCallback, MqttClientFactory mqttClientFactory){

        this.lightCallback = lightCallback;
        this.mqttClientFactory = mqttClientFactory;
    }

    public void subscribe(){
        try {
            MqttClient subscriber = mqttClientFactory.create(BROKER_URL);

            subscriber.setCallback(lightCallback);
            subscriber.connect();

            subscriber.subscribe(TOPIC);
        } catch (MqttException e) {
            new RuntimeException(e);
        }
    }
}

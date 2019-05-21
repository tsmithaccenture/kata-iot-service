package com.accenture.mqtt;

import com.accenture.Store;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LightCallback implements MqttCallback {

    @Resource
    private Store store;

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message){

        if (message == null || new String(message.getPayload()).equals(""))
            return;

        store.toggleImage();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

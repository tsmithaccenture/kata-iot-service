package com.accenture.mqtt;

import com.accenture.Store;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
public class LightCallback implements MqttCallback {

    @Resource
    private Store store;

    private HashMap<String, String> codes = new HashMap<>();

    public LightCallback(){
        codes.put("ON", "1");
        codes.put("OFF", "0");
    }

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

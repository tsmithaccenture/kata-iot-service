package com.accenture.mqtt;

import com.accenture.IotImage;
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
        String signal = new String(message.getPayload());

        if("on".equals(signal)) {
            store.setImageUrl(IotImage.ON_IMAGE_URL);
        }else{
            store.setImageUrl(IotImage.OFF_IMAGE_URL);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

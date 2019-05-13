package com.accenture.test.mqtt;

import com.accenture.IotImage;
import com.accenture.Store;
import com.accenture.mqtt.LightCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LightCallbackTest {

    @Mock
    private Store store;

    @InjectMocks
    private LightCallback lightCallback;

    @Test
    public void messageArrived_WhenOnIsReceived_ThenUpdateStoreWithOnStatus(){
        lightCallback.messageArrived("Any Topic", new MqttMessage("on".getBytes()));

        verify(store).setImageUrl(IotImage.ON_IMAGE_URL);
    }

    @Test
    public void messageArrived_WhenOffIsReceived_ThenUpdateStoreWithOffStatus(){
        lightCallback.messageArrived("Any Topic", new MqttMessage("off".getBytes()));

        verify(store).setImageUrl(IotImage.OFF_IMAGE_URL);
    }
}

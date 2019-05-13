package com.accenture.mqtt;

import com.accenture.IotImage;
import com.accenture.Store;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

    @Test
    public void messageArrived_WhenEmptyStringIsReceived_ThenNothingHappens(){
        lightCallback.messageArrived("Any Topic", new MqttMessage("".getBytes()));

        verifyNoMoreInteractions(store);
    }

    @Test
    public void messageArrived_WhenNullIsReceived_ThenNothingHappens(){
        lightCallback.messageArrived("Any Topic", null);

        verifyNoMoreInteractions(store);
    }

    @Test
    public void connectionLost_DoesNothing(){
        lightCallback.connectionLost(null);

        verifyNoMoreInteractions(store);
    }

    @Test
    public void deliveryComplete_DoesNothing(){
        lightCallback.deliveryComplete(null);

        verifyNoMoreInteractions(store);
    }
}

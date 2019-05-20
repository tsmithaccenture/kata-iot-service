package com.accenture.mqtt;

import com.accenture.IotImage;
import com.accenture.Store;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LightCallbackTest {

    private final static String DEFAULT_MESSAGE = "default";

    @Mock
    private Store store;

    @InjectMocks
    private LightCallback lightCallback;

    @Test
    public void messageArrived_GivenStoreHasOffStatus_WhenAnyMessageIsReceived_ThenUpdateStoreWithOnStatus(){
        when(store.getImageUrl()).thenReturn(IotImage.OFF_IMAGE_URL);

        lightCallback.messageArrived("Any Topic", new MqttMessage(DEFAULT_MESSAGE.getBytes()));

        verify(store).setImageUrl(IotImage.ON_IMAGE_URL);
    }

    @Test
    public void messageArrived_GivenStoreHasOnStatus_WhenAnyMessageIsReceived_ThenUpdateStoreWithOffStatus(){
        when(store.getImageUrl()).thenReturn(IotImage.ON_IMAGE_URL);

        lightCallback.messageArrived("Any Topic", new MqttMessage(DEFAULT_MESSAGE.getBytes()));

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

package com.accenture.mqtt;

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
    public void messageArrived_GivenStoreHasOffStatus_WhenAnyMessageIsReceived_ThenImageIsToggled(){
        lightCallback.messageArrived("Any Topic", new MqttMessage(DEFAULT_MESSAGE.getBytes()));

        verify(store).toggleImage();
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

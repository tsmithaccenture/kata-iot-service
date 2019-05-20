package com.accenture.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.accenture.mqtt.IotMqtt.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LightSwitchSubscriberTest {

    @Mock
    private LightCallback lightCallback;

    @Mock
    private MqttClientFactory mqttClientFactory;

    @Mock
    private MqttClient subscriber;

    @InjectMocks
    private LightSwitchSubscriber lightSwitchSubscriber;


    @Test
    public void subscribe_ThenTopicIsSubscribedTo() throws Exception {
        when(mqttClientFactory.create(BROKER_URL)).thenReturn(subscriber);

        lightSwitchSubscriber.subscribe();

        InOrder orderVerifier = Mockito.inOrder(subscriber);

        orderVerifier.verify(subscriber).setCallback(lightCallback);
        orderVerifier.verify(subscriber).connect();
        orderVerifier.verify(subscriber).subscribe(TOPIC);
    }
}

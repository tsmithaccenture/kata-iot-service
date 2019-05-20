package com.accenture.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static com.accenture.mqtt.IotMqtt.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LightPublisherTest {

    @Mock
    private MqttClientFactory mqttClientFactory;

    @Mock
    private MqttClient publisher;

    @InjectMocks
    private LightPublisher lightPublisher;

    @Test
    public void publish_ThenTopicIsPublished() throws Exception {
        when(mqttClientFactory.create(BROKER_URL)).thenReturn(publisher);

        lightPublisher.publish();

        InOrder orderVerifier = Mockito.inOrder(publisher);

        orderVerifier.verify(publisher).connect();

        orderVerifier.verify(publisher).publish(refEq(TOPIC), refEq(new MqttMessage(MESSAGE.getBytes())));
    }
}

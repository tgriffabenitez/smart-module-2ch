package com.tmg.tempsensor.configurations;

import com.tmg.tempsensor.handlers.MqttHandler;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

@Configuration
public class MqttConfiguration {

    @Value("${spring.mqtt.host}")
    private String mqttHost;
    @Value("${spring.mqtt.port}")
    private String mqttPort;
    @Value("${spring.mqtt.username}")
    private String mqttUsername;
    @Value("${spring.mqtt.password}")
    private String mqttPassword;
    @Value("${spring.mqtt.generalTopic}")
    private String defaultTopic;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{String.format("tcp://%s:%s", mqttHost, mqttPort)});
        options.setUserName(mqttUsername);
        options.setPassword(mqttPassword.toCharArray());
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound(MqttPahoClientFactory mqttClientFactory) {
        return new MqttPahoMessageDrivenChannelAdapter(MqttAsyncClient.generateClientId(), mqttClientFactory, defaultTopic);
    }

    @Bean
    public IntegrationFlow mqttInboundFlow(MqttPahoMessageDrivenChannelAdapter mqttInboundAdapter, MqttHandler mqttHandler) {
        return IntegrationFlow.from(mqttInboundAdapter).handle(mqttHandler).get();
    }
}
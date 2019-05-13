package com.accenture;

import com.accenture.mqtt.LightSwitchSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        LightSwitchSubscriber lightSwitchSubscriber = context.getBean(LightSwitchSubscriber.class);

        lightSwitchSubscriber.subscribe();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }
}

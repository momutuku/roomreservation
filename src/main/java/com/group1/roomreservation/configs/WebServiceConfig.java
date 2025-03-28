package com.group1.roomreservation.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "hotelbooking")
    public DefaultWsdl11Definition defaultWsdl11Definition(SimpleXsdSchema hotelBookingSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("HotelBookingPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://example.com/hotelbooking");
        definition.setSchema(hotelBookingSchema);
        return definition;
    }

    @Bean
    public SimpleXsdSchema hotelBookingSchema() {
        return new SimpleXsdSchema(new ClassPathResource("wsdl/hotel-booking.xsd"));
    }
}
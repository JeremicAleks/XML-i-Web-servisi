package com.centralapi.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;



@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
	    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	    servlet.setApplicationContext(applicationContext);
	    servlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	@Bean(name = "room")
	public DefaultWsdl11Definition defaultWsdl11DefinitionRooms(XsdSchema roomsSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("RoomsPort");
	    wsdl11Definition.setLocationUri("/ws/rooms");
	    wsdl11Definition.setTargetNamespace("http://www.xml-ftn.xml.domain.centralapi.com/Rooms");
	    wsdl11Definition.setSchema(roomsSchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema roomsSchema() {
	    return new SimpleXsdSchema(new ClassPathResource("Rooms.xsd"));
	}
	
	@Bean(name = "reservation")
	public DefaultWsdl11Definition defaultWsdl11DefinitionReservation(XsdSchema reservationSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("ReservationPort");
	    wsdl11Definition.setLocationUri("/ws/reservation");
	    wsdl11Definition.setTargetNamespace("http://www.xml-ftn.xml.domain.centralapi.com/Reservation");
	    wsdl11Definition.setSchema(reservationSchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema reservationSchema() {
	    return new SimpleXsdSchema(new ClassPathResource("Reservation.xsd"));
	}
	
	@Bean(name = "users")
	public DefaultWsdl11Definition defaultWsdl11DefinitionUser(XsdSchema usersSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("UsersPort");
	    wsdl11Definition.setLocationUri("/ws/users");
	    wsdl11Definition.setTargetNamespace("http://www.xml-ftn.xml.domain.centralapi.com/Users");
	    wsdl11Definition.setSchema(usersSchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema usersSchema() {
	    return new SimpleXsdSchema(new ClassPathResource("Users.xsd"));
	}
	
	@Bean(name = "locations")
	public DefaultWsdl11Definition defaultWsdl11DefinitionLocation(XsdSchema locationsSchema) {
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("LocationsPort");
	    wsdl11Definition.setLocationUri("/ws/locations");
	    wsdl11Definition.setTargetNamespace("http://www.xml-ftn.xml.domain.centralapi.com/Location");
	    wsdl11Definition.setSchema(locationsSchema);
	    return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema locationsSchema() {
	    return new SimpleXsdSchema(new ClassPathResource("Location.xsd"));
	}
	
}

package com.agentapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SOAPConnector extends WebServiceGatewaySupport {
	
	@Autowired
	WebServiceTemplate webServiceTemplate;
	
    public Object callWebService(String url, Object request){
        return webServiceTemplate.marshalSendAndReceive(url, request);
    }

}

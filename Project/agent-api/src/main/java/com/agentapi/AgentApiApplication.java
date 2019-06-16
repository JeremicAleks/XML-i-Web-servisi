package com.agentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.centralapi")
@ComponentScan("com.agentapi")

public class AgentApiApplication {

	static {

		// Kopirati sertifikate u C
		// Nalaze se target/class
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("javax.net.ssl.trustStore", "c://agentTrustStore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "MegaTravel");
		System.setProperty("javax.net.ssl.keyStore", "c://agent.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "MegaTravel");

		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setPackagesToScan("com.agentapi.com.centralapi.domain.xml");

		return jaxb2Marshaller;
	}

	@Bean
	@LoadBalanced
	public WebServiceTemplate getWebServiceTempalte() {
		WebServiceTemplate  wt= new WebServiceTemplate();
		wt.setMarshaller(jaxb2Marshaller());
		wt.setUnmarshaller(jaxb2Marshaller());
		
		return wt;
	}

	public static void main(String[] args) {
		SpringApplication.run(AgentApiApplication.class, args);
	}

}

package com.centralapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class CentralApiApplication {
	
	static {

		// Kopirati sertifikate u C
		// Nalaze se target/class
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("javax.net.ssl.trustStore", "c://centralTrustStore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "MegaTravel");
		System.setProperty("javax.net.ssl.keyStore", "c://central.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "MegaTravel");

		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return true;
			}
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(CentralApiApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}

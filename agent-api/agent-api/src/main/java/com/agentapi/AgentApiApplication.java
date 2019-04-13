package com.agentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentApiApplication {

	static {
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("javax.net.ssl.trustStore", "c://agentTrustStore.p12");
		System.setProperty("javax.net.ssl.trustStorePassword", "MegaTravel");
		System.setProperty("javax.net.ssl.keyStore",  "c://agent.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "MegaTravel");
		
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				new javax.net.ssl.HostnameVerifier() {

					public boolean verify(String hostname,
							javax.net.ssl.SSLSession sslSession) {
						if (hostname.equals("localhost")) {
							return true;
						}
						return false;
					}
				});
	}
	public static void main(String[] args) {
		SpringApplication.run(AgentApiApplication.class, args);
	}

}

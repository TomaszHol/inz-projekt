package com.holeksa;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import java.util.*;

/*@SpringBootApplication
public class CsrfVulnerabilityApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CsrfVulnerabilityApplication.class, args);
	}



}*/
@SpringBootApplication
public class DivarioVulnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivarioVulnApplication.class, args);
	}

}

package com.prodigy.contactmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	

	@Bean
	 public OpenAPI openApiInformation() {
	  Server localServer = new Server()
	                      .url("http://localhost:8080")
	                      .description("Localhost Server URL");

	  Contact contact = new Contact()
	                    .email("akshadashinde4858@gmail.com")
	                    .name("Akshada Shinde");

	  Info info = new io.swagger.v3.oas.models.info.Info()
	              .contact(contact)
	              .description("Developed a program that allows users to store and manage contact information. The program should provide options to add a new contact by entering their name, phone number, and email address. It should also allow users to view their contact list, edit existing contacts, and delete contacts if needed. The program should store the contacts in memory or in a file for persistent storage.")
	              .summary("Designed Contact management application using Spring Boot 3, Spring Security, JWT and also created API documentation using Springdoc Open API")
	              .title("Contact Management System")
	              .version("V1.0.0")
	              .license(new License().name("Copyright ©2023 Prodigy InfoTech").url("https://prodigyinfotech.dev/"));

	  return new OpenAPI().info(info).addServersItem(localServer);
	 }

}

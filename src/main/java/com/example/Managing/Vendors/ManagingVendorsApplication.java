package com.example.Managing.Vendors;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Vendor Management System",
				description = "The Vendor Management System is a web-based application built using Spring Boot and MySQL database. This system allows users to manage vendors and perform CRUD operations (Create, Read, Update, Delete) on vendor data. The system is deployed on Amazon Web Services (AWS) and can be accessed from anywhere with an internet connection.",
				version = "1.0.0",
				termsOfService = "Contact for details",
				contact = @Contact(
						name = "Vivek Singhania",
						email = "viveksinghania@gmail.com",
						url = "https://github.com/itsviveksinghania"
				),
				license = @License(
						name = "Vivek Singhania",
						url = "Run Code"
				)

		)
)
public class ManagingVendorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagingVendorsApplication.class, args);
	}

}

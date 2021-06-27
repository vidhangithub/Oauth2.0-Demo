package com.vidhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@ComponentScan("com.vidhan")
@EntityScan("com.vidhan.models")
@EnableJpaRepositories("com.vidhan.repositories")

//Enable own auth server
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class VidEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidEmployeeApplication.class, args);
	}

}

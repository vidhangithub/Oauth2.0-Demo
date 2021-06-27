package com.vidhan.ownauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@ComponentScan("com.vidhan.ownauthserver")
@EntityScan(basePackages= {"com.vidhan.ownauthserver.model"})
@EnableJpaRepositories("com.vidhan.ownauthserver.repository")
public class VidhanOwnAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidhanOwnAuthServerApplication.class, args);
	}

}

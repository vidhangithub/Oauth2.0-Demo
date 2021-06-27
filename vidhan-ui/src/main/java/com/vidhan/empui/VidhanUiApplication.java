package com.vidhan.empui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vidhan.empui")
public class VidhanUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidhanUiApplication.class, args);
	}

}

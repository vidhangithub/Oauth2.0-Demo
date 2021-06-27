package com.vidhan.ownauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class UserConfiguration  /*extends GlobalAuthenticationConfigurerAdapter*/ {
    //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  /*  @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("vidhan").password(passwordEncoder.encode("chandra")).
                roles("ADMIN","USER").authorities("CAN_READ","CAN_WRITE","CAN_DELETE").
                and().
                withUser("rita").password(passwordEncoder.encode("barik")).roles("USER").authorities("CAN_READ","CAN_WRITE");

    }*/
}

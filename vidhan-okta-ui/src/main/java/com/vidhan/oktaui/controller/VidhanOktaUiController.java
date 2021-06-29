package com.vidhan.oktaui.controller;

import com.vidhan.oktaui.model.OktaUIEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Controller
public class VidhanOktaUiController extends WebSecurityConfigurerAdapter {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    RestTemplate restTemplate;

    public VidhanOktaUiController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }

    @RequestMapping(value = "/")
    public String loadUI() {
        return "home";
    }

    @RequestMapping(value = "/secure")
    public String loadSecuredUI() {
        return "secure";
    }

    @RequestMapping(value = "/employees")
    public String loadCustomers(Model model ) {
        HttpHeaders httpHeaders = new HttpHeaders() {{
            set("Authorization", "Bearer " +getAccessToken());
        }};
        HttpEntity<OktaUIEmployee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<OktaUIEmployee[]> responseEntity = restTemplate.exchange("http://localhost:9191/api/v1/employees",
                    HttpMethod.GET, employeeHttpEntity, OktaUIEmployee[].class);
            model.addAttribute("employees", responseEntity.getBody());
        } catch (HttpStatusCodeException e) {
            ResponseEntity responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
            model.addAttribute("error", responseEntity);
        }
        return "secure";
    }

    private String getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client =
                authorizedClientService.loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());
        return client.getAccessToken().getTokenValue();
    }

}

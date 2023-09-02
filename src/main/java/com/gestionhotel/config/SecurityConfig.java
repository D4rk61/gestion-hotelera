/*
package com.gestionhotel.config;

import lombok.experimental.WithBy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration  @EnableWebSecurity
public class SecurityConfig {


 */
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .cors().disable()
                .authorizeHttpRequests((auth) -> {

                    /*
                    auth.requestMatchers(HttpMethod.GET, "/api/hotel/list").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/hotel/save").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                    */

    /*
                })

                .formLogin()
                .successHandler(successHandler())
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")

                .and().sessionFixation().migrateSession()
                .and().httpBasic();

        return httpSecurity.build();

    }

    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/api/hotel/list");
        };

    }
     */

/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers(HttpMethod.POST, "/api/reserva/create").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .httpBasic();


        return httpSecurity.build();
    }


}

*/
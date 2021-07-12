package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.config;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.security.JWTFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .mvcMatchers(HttpMethod.GET, "/fake").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/v1/fresh-products/inboundorder").hasRole("SUPERVISOR")
                .mvcMatchers(HttpMethod.PUT, "/api/v1/fresh-products/inboundorder").hasRole("SUPERVISOR")
                .mvcMatchers(HttpMethod.GET, "/api/v1/fresh-products/list").hasRole("SUPERVISOR")
                .mvcMatchers(HttpMethod.GET, "/api/v1/fresh-products/warehouse").hasRole("SUPERVISOR")
                .mvcMatchers(HttpMethod.GET, "/api/v1/fresh-products/due-date").hasRole("SUPERVISOR")
                .mvcMatchers(HttpMethod.GET, "/api/v1/fresh-products").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.POST, "/api/v1/fresh-products/orders").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.POST, "/api/v1/fresh-products/orders").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.GET, "/api/v1/fresh-products/orders").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.PUT, "/api/v1/fresh-products/orders").hasRole("CLIENT")
                .mvcMatchers(HttpMethod.GET, "/backoffice/list_accounts").hasRole("SUPERVISOR")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }

}

package com.kal.club.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .stateless((false)); //testing
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**")
//                .hasAnyRole("ADMIN")
                .permitAll()
//                .antMatchers("chapters/chapters/all", "roles/**")
//                .permitAll()
//                .antMatchers("/chapters/chapters", "/units/units")
//                .hasAnyRole("ADMIN", "EDUCATOR", "PREMIUM")
//                //.antMatchers("/roles/**")
//                //.hasAnyRole("ADMIN")
//                .antMatchers("/units/**")
//                .hasAnyRole("ADMIN", "EDUCATOR")
//                .antMatchers("/users/userinfo", "/logout")
//                .authenticated()
//                .antMatchers("users/user/exist/**")
//                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();
    }
}
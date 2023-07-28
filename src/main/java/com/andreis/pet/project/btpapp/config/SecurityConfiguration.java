package com.andreis.pet.project.btpapp.config;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    XsuaaServiceConfiguration xsuaaServiceConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                // session is created by approuter
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // demand specific scopes depending on intended request
                .authorizeRequests()

                .antMatchers("/**").authenticated()
                //                .antMatchers("/**").hasAuthority("Display")
                .anyRequest().denyAll() // deny anything not configured above
                .and()
                .oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(getJwtAuthoritiesConverter());

    }

    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthoritiesConverter() {
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true); // not applicable in case of multiple Xsuaa bindings!
        return converter;
    }
}

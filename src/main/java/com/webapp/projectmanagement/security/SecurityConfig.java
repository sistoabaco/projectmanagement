package com.webapp.projectmanagement.security;

import com.webapp.projectmanagement.filter.CustomAuthenticationFilter;
import com.webapp.projectmanagement.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/", "/h2", "/login", "/refreshToken").permitAll();
//        http.authorizeRequests().antMatchers("/saveProject", "/saveCategory", "/savePartner").permitAll();
        http.authorizeRequests().antMatchers(GET, "/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/**").hasAnyAuthority("P_USER");
//        http.authorizeRequests().antMatchers(POST, "/saveEmployee").hasAnyAuthority("P_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors(c -> {
            CorsConfigurationSource cs =  r ->{
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
                cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowCredentials(true);
                return cc;
            };
            c.configurationSource(cs);
        });

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}

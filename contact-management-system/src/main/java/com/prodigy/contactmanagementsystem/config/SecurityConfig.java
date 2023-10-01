package com.prodigy.contactmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.prodigy.contactmanagementsystem.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            HandlerMappingIntrospector introspector) throws Exception {
    	
    	MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    	
    	
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                		mvcMatcherBuilder.pattern("/cms/user"), 
                		mvcMatcherBuilder.pattern("/authenticate"),
                		mvcMatcherBuilder.pattern("/swagger-ui/**"),
                		mvcMatcherBuilder.pattern("/v2/api-docs"),
                		mvcMatcherBuilder.pattern("/swagger-resources"),
                		mvcMatcherBuilder.pattern("/swagger-resources/**"),
                		mvcMatcherBuilder.pattern("/configuration/ui"),
                		mvcMatcherBuilder.pattern("/configuration/security"),
                		mvcMatcherBuilder.pattern("/swagger-ui.html"),
                		mvcMatcherBuilder.pattern("/v3/api-docs/**"),
                        PathRequest.toH2Console()
                 ).permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(mvcMatcherBuilder.pattern("/cms/**")).permitAll()
                .requestMatchers(PathRequest.toH2Console()).authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    
    /*@Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http,
                                                      HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(
                		mvcMatcherBuilder.pattern("/cms/user"), 
                		mvcMatcherBuilder.pattern("/authenticate"),
                		mvcMatcherBuilder.pattern("/swagger-ui/**"),
                        PathRequest.toH2Console()));

        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(mvcMatcherBuilder.pattern("/cms/**")).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).authenticated()
                        .anyRequest().authenticated()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        
        return http.build();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

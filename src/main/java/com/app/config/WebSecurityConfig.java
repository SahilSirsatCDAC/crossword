package com.app.config;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filters.JWTRequestFilter;

@EnableWebSecurity // mandatory
@Configuration // mandatory
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private JWTRequestFilter filter;

	// configure BCryptPassword encode bean
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@SuppressWarnings({ "deprecation", "removal" })
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().
		exceptionHandling().
		authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		}).
		and().
		authorizeHttpRequests()
		.requestMatchers(HttpMethod.DELETE, "/booking/**", "/user/**").hasRole("USER")
		.requestMatchers(HttpMethod.DELETE, "/building/**", "/room/**", "/bed/**").hasAnyRole("OWNER", "ADMIN")
		
		.requestMatchers(HttpMethod.POST, "/image/user/**").hasRole("USER")
		.requestMatchers(HttpMethod.POST, "/image/building/**", "/image/room/**").hasRole("OWNER")
		.requestMatchers(HttpMethod.POST, "/address/add/**").hasAnyRole("USER", "OWNER")
		.requestMatchers(HttpMethod.POST, "/building/**", "/room/inbuilding/**", "/bed/inroom/**").hasRole("OWNER")
		.requestMatchers(HttpMethod.POST, "/booking/bed/**").hasAnyRole("ADMIN", "USER", "OWNER")

//		.requestMatchers(HttpMethod.PUT, "/user/**").hasRole("USER")
		
		.requestMatchers(HttpMethod.GET, "/address/**", "/user/users").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET, "/booking/userBookings/**", "/image/user/**").hasRole("USER")
		.requestMatchers(HttpMethod.GET, "/booking/bedBookings/**").hasRole("OWNER")
//		.antMatchers(HttpMethod.GET, "/user/**").permitAll()
		.requestMatchers(HttpMethod.GET, "/building/**", "/room/inbuilding/**", "/bed/inroom/**",  "/image/building/**", "/image/room/**", "/image/id/**").permitAll()
		
//		.requestMatchers("/auth/**", "/swagger*/**", "/v*/api-docs/**").permitAll()
		.requestMatchers(HttpMethod.OPTIONS).permitAll()
		.requestMatchers("/word/**","/crossword/**","/auth/**","/user/**", "/swagger*/**", "/v*/api-docs/**").permitAll()
		.anyRequest().authenticated().
		and().
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}

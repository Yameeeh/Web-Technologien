package com.webtech.football.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private CustomUserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.userDetailsService = customUserDetailsService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		RequestMatcher postCommentMatcher = new AntPathRequestMatcher("/api/comments", "POST");

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req -> req.requestMatchers("/admin").authenticated().requestMatchers("/forum").authenticated()
								// .requestMatchers(postCommentMatcher).authenticated()
								.requestMatchers("/api/auth/login").permitAll().anyRequest().permitAll())
				.formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/").permitAll())

				.userDetailsService(this.userDetailsService);
		return http.build();
	}

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

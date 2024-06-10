package com.webtech.football.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build());
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN").build());
		return manager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests(
//						authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//								.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN").requestMatchers("/admin/**")
//								.hasAnyRole("ADMIN").requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//								.requestMatchers("/login/**").permitAll().anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults())
//				.userDetailsService(userDetailsService());

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
								.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN").requestMatchers("/admin/**")
								.hasAnyRole("ADMIN").requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
								.requestMatchers("/", "/index.html", "/public/**", "/home", "/login/**").permitAll()
								.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll())
				.userDetailsService(userDetailsService());

		return http.build();
	}

}

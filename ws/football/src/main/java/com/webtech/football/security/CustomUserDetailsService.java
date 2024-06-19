package com.webtech.football.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webtech.football.entities.UserEntity;
import com.webtech.football.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

		Collection<GrantedAuthority> authorities = mapRolesToAuthorities(user.getRole());

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	private Collection<GrantedAuthority> mapRolesToAuthorities(String roles) {
		// Split roles string by comma or any delimiter as per your structure
		String[] roleNames = roles.split(",");

		return Arrays.stream(roleNames).map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

}

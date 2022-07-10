package com.studenttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studenttracker.model.User;
import com.studenttracker.repository.UserRepository;
import com.studenttracker.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return new MyUserDetails(user);
	}
}
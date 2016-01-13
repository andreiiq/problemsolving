package com.badger.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.badger.service.UserService;
import com.badger.service.impl.DefaultUserService;

@Component
public class DBAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();

		try {
			UserDetails userDetails = userService.loadUserByUsername(username);
			if (isUserRegistered(auth, userDetails)) {
				return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
			}
		} catch (UsernameNotFoundException unfe) {
			logger.warn("The user is not in the database" + username, unfe);
			throw new BadCredentialsException("The entered user is not in the system", unfe);
		}

		logger.warn("The user is not in the database" + username);
		throw new BadCredentialsException("The entered user is not in the system");
	}

	private boolean isUserRegistered(Authentication auth, UserDetails userDetails) {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		if (username.equals(userDetails.getUsername()) && password.equals(userDetails.getPassword())) {
			return true;
		}
		return false;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

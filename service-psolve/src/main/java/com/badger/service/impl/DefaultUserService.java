package com.badger.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badger.service.UserService;
import com.badger.util.BuserFileManager;
import com.psolve.dao.StudentDAO;
import com.psolve.model.Student;

@Service
public class DefaultUserService implements UserService {

	private StudentDAO userDAO;

	private BuserFileManager fileManager;

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

	@Override
	@Transactional
	public Student findUserByID(String id) {
		long userCode = findUserCode(id);
		return userDAO.findOne(userCode);
	}

	@Override
	@Transactional
	public Student findUserByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	/**
	 * Method used by spring security to load an username from DB. This is a
	 * custom implementation which retrieves the user by its email.
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Student user = userDAO.findByEmail(email);
		if (user == null) {
			logger.warn("User not in the database: " + email);
			throw new UsernameNotFoundException("Username is not in the DB");
		}
		String password = user.getPassword();

		Collection<GrantedAuthority> autorities = new ArrayList<>();
		autorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails userDetails = new User(email, password, autorities);

		return userDetails;
	}

	@Override
	public void saveUser(Student buser) {
		userDAO.save(buser);
	}

	@Override
	public byte[] getProfileImage(Student buser) {
		try {
			return fileManager.getBuserProfileImage(buser);
		} catch (IOException ioe) {
			logger.error("Could not retrieve the user's:" + buser.getEmail() + "profile picture", ioe);
		}
		return null;
	}

	@Override
	public void setProfileImage(Student buser, InputStream profileImage) {
		fileManager.saveBuserProfileImage(buser, profileImage);
	}

	private long findUserCode(String id) {
		return Long.parseLong(id);
	}
}

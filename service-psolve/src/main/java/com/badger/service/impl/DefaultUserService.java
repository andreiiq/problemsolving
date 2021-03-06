package com.badger.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badger.service.UserService;
import com.badger.util.BuserFileManager;
import com.psolve.dao.StudentRepo;
import com.psolve.dao.UserRepo;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TeacherModel;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private UserRepo<AbstractUserModel> userRepo;

	@Autowired
	private BuserFileManager fileManager;

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

	@Override
	@Transactional
	public StudentModel findUserByID(String id) {
		long userCode = findUserCode(id);
		return studentRepo.findOne(userCode);
	}

	@Override
	public AbstractUserModel getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) auth.getPrincipal();

		return userRepo.findByEmail(email);
	}

	@Override
	@Transactional
	public AbstractUserModel findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	/**
	 * Method used by spring security to load an username from DB. This is a
	 * custom implementation which retrieves the user by its email.
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AbstractUserModel user = userRepo.findByEmail(email);

		if (user == null) {
			logger.warn("User not in the database: " + email);
			throw new UsernameNotFoundException("Username is not in the DB");
		}

		String password = user.getPassword();
		Collection<GrantedAuthority> autorities = new ArrayList<>();

		autorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		if (user instanceof TeacherModel) {
			autorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
		}

		UserDetails userDetails = new User(email, password, autorities);

		return userDetails;
	}

	@Override
	public void saveUser(StudentModel buser) {
		studentRepo.save(buser);
	}

	@Override
	public byte[] getProfileImage(StudentModel buser) {
		try {
			return fileManager.getBuserProfileImage(buser);
		} catch (IOException ioe) {
			logger.error("Could not retrieve the user's:" + buser.getEmail() + "profile picture", ioe);
		}
		return null;
	}

	@Override
	public void setProfileImage(StudentModel buser, InputStream profileImage) {
		fileManager.saveBuserProfileImage(buser, profileImage);
	}

	private long findUserCode(String id) {
		return Long.parseLong(id);
	}
}

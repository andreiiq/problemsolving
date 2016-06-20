package com.badger.service;

import java.io.InputStream;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.psolve.model.AbstractUserModel;
import com.psolve.model.StudentModel;

public interface UserService extends UserDetailsService {
	StudentModel findUserByID(String id);

	AbstractUserModel findUserByEmail(String email);

	void saveUser(StudentModel buser);

	byte[] getProfileImage(StudentModel buser);

	void setProfileImage(StudentModel buser, InputStream profileImage);
	
	AbstractUserModel getCurrentUser();
}
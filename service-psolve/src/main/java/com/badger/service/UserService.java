package com.badger.service;

import java.io.InputStream;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.psolve.model.Student;

public interface UserService extends UserDetailsService {
	Student findUserByID(String id);

	Student findUserByEmail(String email);

	void saveUser(Student buser);

	byte[] getProfileImage(Student buser);

	void setProfileImage(Student buser, InputStream profileImage);
}
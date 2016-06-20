package com.badger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badger.service.AuthorizationService;
import com.badger.service.UserService;
import com.psolve.model.AbstractUserModel;

@Service
public class DefaultAuthorizationService implements AuthorizationService {

	@Autowired
	UserService userService;

	@Override
	public boolean isAuthorized(AbstractUserModel user) {
		AbstractUserModel currentUser = userService.getCurrentUser();

		String email = currentUser.getEmail();
		String userEmail = user.getEmail();

		if (email.equals(userEmail)) {
			return true;
		}

		return false;
	}

}

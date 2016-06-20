package com.badger.service;

import com.psolve.model.AbstractUserModel;

public interface AuthorizationService {
	boolean isAuthorized(AbstractUserModel user);
}

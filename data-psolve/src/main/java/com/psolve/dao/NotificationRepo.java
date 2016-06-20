package com.psolve.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.psolve.model.AbstractNotificationModel;
import com.psolve.model.AbstractUserModel;

@Repository
public interface NotificationRepo<T extends AbstractNotificationModel> extends JpaRepository<T, Long> {
	List<T> findByOwner(AbstractUserModel user);

	long countByUnseenIsFalse();
}

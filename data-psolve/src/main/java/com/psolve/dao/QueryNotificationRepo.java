package com.psolve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.psolve.model.AbstractQueryNotificationModel;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.NotificationStatus;

@Repository
public interface QueryNotificationRepo<T extends AbstractQueryNotificationModel>
		extends NotificationRepo<T>{
	
	List<T> findByOwnerAndStatusOrderByCreatedAtAsc(AbstractUserModel user, NotificationStatus status);

}

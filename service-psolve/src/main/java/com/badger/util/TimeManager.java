package com.badger.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class TimeManager {

	private String getCurrentDate() {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate localDate = currentTime.toLocalDate();

		return localDate.toString();
	}
}

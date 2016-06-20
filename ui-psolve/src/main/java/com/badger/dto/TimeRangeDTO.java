package com.badger.dto;

public class TimeRangeDTO {
	private String from;
	private String to;
	private int weekOfYear;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + weekOfYear;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeRangeDTO other = (TimeRangeDTO) obj;
		if (weekOfYear != other.weekOfYear)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return from + " - " + to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(int weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

}

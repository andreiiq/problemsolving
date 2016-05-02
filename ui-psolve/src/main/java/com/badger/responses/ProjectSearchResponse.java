package com.badger.responses;

import java.util.List;

/**
 * Created by andre on 4/17/2016.
 */
public class ProjectSearchResponse {
	private List<TaskModelResponse> tasks;
	private int numberOfPages;
	private int currentPage;

	public List<TaskModelResponse> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskModelResponse> tasks) {
		this.tasks = tasks;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}

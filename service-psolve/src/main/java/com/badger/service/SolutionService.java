package com.badger.service;

import java.util.List;

import com.psolve.model.SolutionModel;

public interface SolutionService {
	void uploadSolution(byte[] solution, long taskId);

	List<SolutionModel> getSolutionsForCurrentTeacher();

	String getSolutionPath(long solutionId);
}

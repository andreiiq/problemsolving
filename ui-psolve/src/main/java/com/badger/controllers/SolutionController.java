package com.badger.controllers;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.badger.dto.SolutionDTO;
import com.badger.dto.TimeRangeDTO;
import com.badger.service.SolutionService;
import com.psolve.model.SolutionModel;

@Controller
public class SolutionController {

	@Autowired
	SolutionService solutionService;

	@RequestMapping(value = "/uploadSolution", method = RequestMethod.POST)
	public ResponseEntity<String> uploadSolution(@RequestParam MultipartFile file, @RequestParam long taskId) {
		try {

			byte[] fileContent = file.getBytes();

			solutionService.uploadSolution(fileContent, taskId);

		} catch (IOException ioe) {
			// TO-DO Exception
			ioe.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/teacher/downloadSolution/{fileName}", method = RequestMethod.GET, produces = "application/x-rar-compressed")
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("fileName") long fileName, HttpServletResponse response) {

		response.setContentType("application/x-rar-compressed");
		response.setHeader("Content-Disposition", "filename=solution.rar");
		return new FileSystemResource(solutionService.getSolutionPath(fileName));
	}

	@ResponseBody
	@RequestMapping(value = "/teacher/getSolutions", method = RequestMethod.GET)
	public Map<TimeRangeDTO, List<SolutionDTO>> getAllSolutions() {
		List<SolutionModel> solutions = solutionService.getSolutionsForCurrentTeacher();

		return buildSolutionsDTO(solutions);

	}

	private Map<TimeRangeDTO, List<SolutionDTO>> buildSolutionsDTO(List<SolutionModel> solutionModels) {
		Map<TimeRangeDTO, List<SolutionDTO>> solutions = new HashMap<>();

		for (SolutionModel solution : solutionModels) {
			SolutionDTO solutionDTO = new SolutionDTO();

			solutionDTO.setProjectName(solution.getTask().getTitle());
			solutionDTO.setId(solution.getId());

			Calendar uploadTime = solution.getUploadTime();
			TimeRangeDTO weekRange = getSolutionSubmitedWeek(uploadTime);
			System.out.println(weekRange.getWeekOfYear());
			if (solutions.containsKey(weekRange)) {
				System.out.println("intra");
				List<SolutionDTO> solutionDTOs = solutions.get(weekRange);
				solutionDTOs.add(solutionDTO);
			} else {
				List<SolutionDTO> solutionDTOs = new LinkedList<>();
				solutionDTOs.add(solutionDTO);
				solutions.put(weekRange, solutionDTOs);
			}

		}

		return solutions;

	}

	private TimeRangeDTO getSolutionSubmitedWeek(Calendar uploadTime) {
		TimeRangeDTO timeRangeDTO = new TimeRangeDTO();

		int weekNumber = uploadTime.get(Calendar.WEEK_OF_YEAR) - 1;
		LocalDate week = LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, weekNumber);

		LocalDate start = week.with(DayOfWeek.MONDAY);
		LocalDate end = start.plusDays(6);

		timeRangeDTO.setFrom(start.toString());
		timeRangeDTO.setTo(end.toString());
		timeRangeDTO.setWeekOfYear(weekNumber);

		return timeRangeDTO;
	}

}

package com.badger.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.badger.dto.BasicInfoDTO;
import com.badger.dto.CoursePointsDTO;
import com.badger.dto.SkillDTO;
import com.badger.form.UserDetails;
import com.badger.service.SkillService;
import com.badger.service.UserService;
import com.badger.util.BuserFileManager;
import com.badger.util.MVCConstants;
import com.badger.util.PageConstants;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.CoursePointsModel;
import com.psolve.model.SkillModel;
import com.psolve.model.StudentModel;

/**
 * Controller which intercepts request related to the user.
 * 
 * @author andre
 *
 */
@Controller
public class UserController {
	/**
	 * Service used for user information retrieval.
	 */
	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	BuserFileManager buserFileManager;

	@Autowired
	SkillService skillService;

	/**
	 * The class logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Method that intercepts the login request.
	 * 
	 * @return The location of the login page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return PageConstants.LOGIN_PAGE;
	}

	@ResponseBody
	@RequestMapping(value = "/profile-image", method = RequestMethod.GET)
	public byte[] getProfileImage() throws IOException {
		AbstractUserModel user = userService.getCurrentUser();
		byte[] image = buserFileManager.getBuserProfileImage(user);
		return image;
	}

	@ResponseBody
	@RequestMapping(value = "/profile-info", method = RequestMethod.GET)
	public BasicInfoDTO getProfileInfo() {
		return buildBasicInfo();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomepage(Model model) {
		Page<SkillModel> skills = skillService.getUserBestSkills();

		model.addAttribute("user", userService.getCurrentUser());
		model.addAttribute("skills", skills.getContent());

		return PageConstants.PROFILE_PAGE;
	}

	/**
	 * Method which intercepts the register request and persists the user.
	 * 
	 * @param userDetails
	 *            The information entered by the user.
	 * @return The location of the register page.
	 * @throws IOException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String getRegisterPage(UserDetails userDetails, HttpServletRequest request) throws IOException {

		StudentModel buser = new StudentModel();
		buser.setEmail(userDetails.getEmail());
		buser.setPassword(userDetails.getPassword());
		buser.setFirstname(userDetails.getFirstname());
		buser.setLastname(userDetails.getLastname());
		buser.setEducation(userDetails.getEducation());

		MultipartFile profileImage = userDetails.getProfileImage();
		userService.setProfileImage(buser, profileImage.getInputStream());
		userService.saveUser(buser);

		authenticateUserAndSetSession(buser, request);
		return MVCConstants.REDIRECT + MVCConstants.WILD_PATH;
	}

	private void authenticateUserAndSetSession(StudentModel buser, HttpServletRequest request) {
		String username = buser.getEmail();
		String password = buser.getPassword();
		org.springframework.security.core.userdetails.UserDetails userDetails = userService
				.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password,
				userDetails.getAuthorities());

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
	}

	private BasicInfoDTO buildBasicInfo() {
		StudentModel userModel = (StudentModel) userService.getCurrentUser();
		List<SkillModel> userSkills = skillService.getUserSkills((StudentModel) userModel);

		List<SkillDTO> skillDtos = new LinkedList<>();
		for (SkillModel skill : userSkills) {
			SkillDTO skillDTO = new SkillDTO();
			skillDTO.setLevel(skill.getLevelModel().getValue());
			skillDTO.setName(skill.getName());
			skillDtos.add(skillDTO);
		}

		List<CoursePointsModel> pointsModels = userModel.getPoints();
		List<CoursePointsDTO> pointsDTOs = new LinkedList<>();
		for (CoursePointsModel pointsModel : pointsModels) {
			CoursePointsDTO coursePointsDTO = new CoursePointsDTO();
			coursePointsDTO.setCourse(pointsModel.getCourse().getTitle());
			coursePointsDTO.setPoints(pointsModel.getPoints());
		}
		BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
		basicInfoDTO.setCoursePointsDTOs(pointsDTOs);
		basicInfoDTO.setSkillDTOs(skillDtos);
		return basicInfoDTO;
	}
}

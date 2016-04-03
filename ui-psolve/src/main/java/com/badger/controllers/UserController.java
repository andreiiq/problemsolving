package com.badger.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.badger.form.UserDetails;
import com.badger.service.UserService;
import com.badger.util.MVCConstants;
import com.badger.util.PageConstants;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomepage() {
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
	public String getRegisterPage(UserDetails userDetails, HttpServletRequest request)
			throws IOException {


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
}

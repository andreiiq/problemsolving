package com.badger.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.psolve.model.AbstractUserModel;
import com.psolve.model.SolutionModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;

@Component
public class BuserFileManager {
	/**
	 * Constant which holds the format of the profile image.
	 */
	private final static String PROFILE_IMG_FORMAT = "jpg";

	private final static String ARCHIVE_FORMAT = "rar";

	private static final Logger logger = LoggerFactory.getLogger(BuserFileManager.class);

	@Value("#{'${file.base_path}' + '${file.profile_image_path}'}")
	private String userImagePath;

	@Value("#{'${file.base_path}' + '${file.solution_path}'}")
	private String solutionPath;

	public void buildBuserProfileImagePath(AbstractUserModel userModel) {
		userModel.setProfileImagePath(userImagePath + "\\" + userModel.getEmail() + "." + PROFILE_IMG_FORMAT);
	}

	public byte[] getBuserProfileImage(AbstractUserModel userModel) throws IOException {
		Path imagePath = Paths.get(userModel.getProfileImagePath() + "." + PROFILE_IMG_FORMAT);
		return Files.readAllBytes(imagePath);
	}

	public void saveBuserProfileImage(AbstractUserModel userModel, InputStream profileImage) {
		buildBuserProfileImagePath(userModel);
		try {
			BufferedImage bufferedImage = ImageIO.read(profileImage);
			ImageIO.write(bufferedImage, PROFILE_IMG_FORMAT, new File(userModel.getProfileImagePath() + ".jpg"));
		} catch (IOException ioe) {
			logger.error("Could not save the user's:" + userModel.getEmail() + "profile picture", ioe);
		}
	}

	public void buildSolutionPath(SolutionModel solutionModel, StudentModel student, TaskModel taskModel) {
		solutionModel.setSolutionPath(solutionPath + "\\" + taskModel.getId() + "." + ARCHIVE_FORMAT);
	}

	public void uploadSolution(SolutionModel solution) {
		try (FileOutputStream fos = new FileOutputStream(solution.getSolutionPath())) {
			fos.write(solution.getFile());
			fos.close();
		} catch (IOException ioe) {
			// TO-DO EXCEPTIONS
			ioe.printStackTrace();
		}
	}

}

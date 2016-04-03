package com.badger.util;

import java.awt.image.BufferedImage;
import java.io.File;
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

import com.psolve.model.StudentModel;

@Component
public class BuserFileManager {
	/**
	 * Constant which holds the format of the profile image.
	 */
	private final static String PROFILE_IMG_FORMAT = "jpg";

	private static final Logger logger = LoggerFactory.getLogger(BuserFileManager.class);

	@Value("#{'${file.base_path}' + '${file.profile_image_path}'}")
	private String buserImagePath;

	public void buildBuserProfileImagePath(StudentModel buser) {
		buser.setProfileImagePath(buserImagePath + "\\" + buser.getEmail() + "." + PROFILE_IMG_FORMAT);
	}

	public byte[] getBuserProfileImage(StudentModel buser) throws IOException {
		Path imagePath = Paths.get(buser.getProfileImagePath() + "." + PROFILE_IMG_FORMAT );
		return Files.readAllBytes(imagePath);
	}

	public void saveBuserProfileImage(StudentModel buser, InputStream profileImage) {
		buildBuserProfileImagePath(buser);
		try {
			BufferedImage bufferedImage = ImageIO.read(profileImage);
			ImageIO.write(bufferedImage, PROFILE_IMG_FORMAT, new File(buser.getProfileImagePath() + ".jpg"));
		} catch (IOException ioe) {
			logger.error("Could not save the user's:" + buser.getEmail() + "profile picture", ioe);
		}
	}

}

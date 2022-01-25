package com.flipkart.service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleDriveService {

	public String uploadMainPhoto(File image);

	public String createSecondaryFolder(String folderName);

	public String uploadSecondaryPhoto(File image, String folderName);

}

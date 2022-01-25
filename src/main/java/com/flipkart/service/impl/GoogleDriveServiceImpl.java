package com.flipkart.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flipkart.service.GoogleDriveService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@Service
public class GoogleDriveServiceImpl implements GoogleDriveService {

	private final String APPLICATION_NAME = "Flipkart";

	private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private final String TOKENS_DIRECTORY_PATH = "tokens";

	private final String MAIN_PHOTOS_PATH = "1Yy0PG8uOW-Ys6q0p6iQJwG4YBKMiRDxR";

	private final String SECONDARY_PHOTOS_PATH = "1a5wbm4mD5R_AEz_UhpEGAPGlFhC4DzRV";

	private final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	private final String FOLDER_MIME_TYPE = "application/vnd.google-apps.folder";

	@Value("${google.secret.key.path}")
	private String CREDENTIALS_FILE_PATH;

	private Drive driveService;

	@PostConstruct
	public void init() {
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			InputStream in = getClass().getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
			if (in == null) {
				throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
			}
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

			// Build flow and trigger user authorization request.
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
					clientSecrets, SCOPES)
							.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
							.setAccessType("offline").build();
			LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
			Credential credential = new AuthorizationCodeInstalledApp(flow, receiver)
					.authorize("sagarkalra03@gmail.com");
			this.driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();

		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}

	}

	public void read() throws IOException, GeneralSecurityException {

		FileList result = driveService.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)")
				.execute();
		List<File> files = result.getFiles();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.println(file.getName() + "  " + file.getId());
			}
		}
	}

	private String fileUpload(java.io.File image, String parent) throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName(image.getName());
		fileMetadata.setParents(Arrays.asList(parent));

		FileContent mediaContent = new FileContent("image/jpeg", image);

		File file = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();
		return file.getId();
	}

	@Override
	public String uploadMainPhoto(java.io.File file) {
		try {
			return fileUpload(file, MAIN_PHOTOS_PATH);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String createSecondaryFolder(String folderName) {
		File fileMetadata = new File();

		fileMetadata.setName(folderName);
		fileMetadata.setMimeType(FOLDER_MIME_TYPE);
		fileMetadata.setParents(Collections.singletonList(SECONDARY_PHOTOS_PATH));

		File file = null;
		try {
			file = driveService.files().create(fileMetadata).setFields("id").execute();
			return file.getId();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String uploadSecondaryPhoto(java.io.File file, String folderName) {
		try {
			return fileUpload(file, folderName);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

}
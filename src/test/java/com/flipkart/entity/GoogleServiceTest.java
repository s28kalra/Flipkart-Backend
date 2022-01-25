package com.flipkart.entity;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flipkart.service.GoogleDriveService;

@SpringBootTest
public class GoogleServiceTest {
	
	@Autowired
	private GoogleDriveService driveService;
	
	String path="C:\\Users\\10670576\\Downloads\\main-photos\\google-nest-mini.jpeg";
	
	@Test
	public void testUploadMainPhoto() {
		File image= new File(path);
		System.out.println(driveService.uploadMainPhoto(image));
	}
	
	@Test
	public void testCreateSecondaryFolder() {
		File image = new File(path);
		System.out.println(driveService.createSecondaryFolder(image.getName().substring(0, image.getName().lastIndexOf('.'))));
	}

}

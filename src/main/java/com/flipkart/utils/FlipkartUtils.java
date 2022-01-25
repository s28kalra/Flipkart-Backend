package com.flipkart.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.flipkart.dto.PageInfo;

public class FlipkartUtils {
	
	public static PageInfo getPageInfoFromPage(Page page) {
		return new PageInfo(page.getNumber(), page.getNumberOfElements(), page.getSize(),
				page.getTotalElements(), page.getTotalPages(), page.getSort());
	}
	
	public static File getFileFromMultipartFile(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		file.createNewFile();
		FileOutputStream fos= new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();
				
		return file;
	}
	

}

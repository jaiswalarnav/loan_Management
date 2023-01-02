package com.user.loan_Management.model.support;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadSupport {
	
	public final String uploadDir="/Users/admin/LoanApplicationDoc";
	
	public boolean uploadFile(MultipartFile multipartFile)throws Exception
	{
		boolean f=false;
		try {
			 Files.copy(multipartFile.getInputStream(), Paths.get(uploadDir+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			 f=true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return f;
	}

}

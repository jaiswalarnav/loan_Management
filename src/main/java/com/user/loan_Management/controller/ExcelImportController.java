package com.user.loan_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.csv.ExcelHelper;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.model.support.FileUploadSupport;

@RestController
public class ExcelImportController {

	@Autowired
	private FileUploadSupport fileUploadSupport;

	@Autowired
	private ExcelHelper excelHelper;

	@PostMapping("/import-excel")
	public DataResponse excelImport(@RequestParam("excelFile") MultipartFile multipartFile) {

		try {

			if (multipartFile.isEmpty())
				throw new RuntimeException(ConstantMessage.FILE_NOT_FOUND);

			if (!multipartFile.getContentType()
					.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				throw new RuntimeException(ConstantMessage.FILE_EXTENSION_ERROR);

			boolean uploadFile = fileUploadSupport.uploadExcelFile(multipartFile);

			if (!uploadFile)
				throw new RuntimeException(ConstantMessage.INTERNAL_SERVER_ERROR);

			excelHelper.saveAadhar(multipartFile);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK, null);
		} catch (Exception e) {

			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());

		}

	}

}

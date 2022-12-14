package com.user.loan_Management.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.user.loan_Management.model.Aadhar;
import com.user.loan_Management.repository.AadharRepository;
import io.micrometer.common.util.StringUtils;

@Component
public class ExcelHelper {

	@Autowired
	AadharRepository aadharRepository;


	public void saveAadhar(MultipartFile multipartFile) {
		try

		{

			InputStream targetStream = new FileInputStream(
					new ClassPathResource("static/excel/" + multipartFile.getOriginalFilename()).getFile());
			List<Aadhar> aadhars = excelToAadhar(targetStream);
			aadharRepository.saveAll(aadhars);
			new File(new ClassPathResource("static/excel").getFile(), multipartFile.getOriginalFilename()).delete();

		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public static List<Aadhar> excelToAadhar(InputStream is) {
		try {

			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rows = sheet.iterator();

			List<Aadhar> aadhars = new ArrayList<Aadhar>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				if (isRowEmpty(currentRow))
					continue;

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Aadhar aadhar = new Aadhar();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						aadhar.setAadharNo(currentCell.getStringCellValue());
						break;

					case 1:
						aadhar.setName(currentCell.getStringCellValue());
						break;

					case 2:
						aadhar.setDob(currentCell.getStringCellValue());
						break;

					case 3:
						aadhar.setGender(currentCell.getStringCellValue());
						break;

					case 4:
						aadhar.setPhoneNo(currentCell.getStringCellValue());
						break;

					case 5:
						aadhar.setEmail(currentCell.getStringCellValue());
						break;

					case 6:
						aadhar.setAddress(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				aadhars.add(aadhar);
			}

			workbook.close();

			return aadhars;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}

	}

	public static Boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0)
			return true;
		for (int cellNum = row.getFirstCellNum(); cellNum <= row.getLastCellNum(); cellNum++) {
			Cell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
				return false;
			}
		}
		return true;
	}
}

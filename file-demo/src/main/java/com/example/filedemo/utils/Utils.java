package com.example.filedemo.utils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

public class Utils {

	public static int countRecords(MultipartFile file) {
		int count = 0;
		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReader(reader, '|','\'',1);

			String[] record = null;

			HashSet<String> srcId = new HashSet<>();
			while ((record = csvReader.readNext()) != null) {
				srcId.add(record[0] + record[3]);
			}

			count = srcId.size();
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return count;
	}

	public static int allRecordCount(MultipartFile file) {
		int count = 0;
		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			//CSVReader csvReader = new CSVReader(reader, '|','\'',1);
			CSVReader csvReader = new CSVReader(reader, '|','\'',1);
			String[] record = null;

			ArrayList<String> allRecs = new ArrayList<>();
			while ((record = csvReader.readNext()) != null) {
				allRecs.add(record[0] + record[3]);
			}
			count	=	allRecs.size();
			csvReader.close();
			return	count;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}
}

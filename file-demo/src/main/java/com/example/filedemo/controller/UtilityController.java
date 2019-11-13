package com.example.filedemo.controller;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filedemo.model.OutputContent_1;
import com.example.filedemo.model.OutputContents;
import com.example.filedemo.service.FileStorageService;

@RestController
@RequestMapping("/utility/")
public class UtilityController {

	public static int matchedCount = 0;
	public static int unMatchedCount = 0;
	public static int totalCount = 0;

	@Autowired
	private FileStorageService fileStorageService;

	JSONParser parser = new JSONParser();

	@GetMapping("/count")
	public int getRowCount() {
		// Unique Values
		return totalCount;
	}

	@GetMapping("/unmatched")
	public int getUnMatchedRecords() {
		// Logic Change
		return unMatchedCount;
	}

	@GetMapping("/matched")
	public int getMatchedRecords() {
		return matchedCount;
	}

	@GetMapping("/matchPercentage")
	public int getMatchedPercentage() {
		float percentageCal = (float) matchedCount / totalCount * 100;
		int percentage	=	Math.round(percentageCal);
		System.out.println("Percentage: "+percentage);
		return percentage;
	}

	@GetMapping("/match/result")
	public ResponseEntity<List<OutputContent_1>> getOpContent() {

		try {

			OutputContent_1 opCont;
			String pathToOp = ("C:\\Users\\ys19299\\output\\match_output.json");
			/* String pathToOp =
			 ("/home/ec2-user/ext_match_util/dev/inbound/match_output.json");*/
			JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(pathToOp)));
			List<OutputContent_1> opList = new ArrayList<>();

			System.out.println(jsonArray);

			for (Object o : jsonArray) {
				opCont = new OutputContent_1();
				JSONObject val = (JSONObject) o;
				opCont.setInputRecordId(val.get("inputRecordId").toString());
				// opCont.setMatchedAttributes((List<HashMap<String, Object>>)
				// val.get("matchedEntities"));
				opCont.setMatchedEntities((List<HashMap<String, Object>>) val.get("matchedEntities"));
				opList.add(opCont);
			}

			System.out.println(opList.size());

			unMatchedCount = getUnMatched(opList);

			// opList.removeIf(op->!seen.add(op.getUriSource()));
			// opCont.setUriSource();
			// ResponseEntity.ok(body);
			return new ResponseEntity<List<OutputContent_1>>(opList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return (ResponseEntity<List<OutputContent_1>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public static int getUnMatched(List<OutputContent_1> opCont) {
		List<String> ipId = new ArrayList<>();
		for (OutputContent_1 op : opCont) {
			String ipIdStr = op.getInputRecordId();
			ipId.add(ipIdStr);
		}

		HashSet<Object> seen = new HashSet<>();
		ipId.removeIf(op -> !seen.add(op));

		UtilityController.totalCount = ipId.size();

		List<String> ipIdNtNull = new ArrayList<>();
		for (OutputContent_1 op : opCont) {
			if (!op.getMatchedEntities().isEmpty()) {
				String ipIdStr = op.getInputRecordId();
				ipIdNtNull.add(ipIdStr);
			} else {
				System.out.println("Null encountered");
			}
		}

		UtilityController.matchedCount = ipIdNtNull.size();

		List<String> ipUnMatched = new ArrayList<>();
		for (String ip : ipId) {
			if (ipIdNtNull.contains(ip)) {
				System.out.println("Exists");
			} else {
				ipUnMatched.add(ip);
			}
		}
		return ipUnMatched.size();
	}

}

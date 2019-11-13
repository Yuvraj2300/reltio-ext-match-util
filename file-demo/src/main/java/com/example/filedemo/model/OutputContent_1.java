package com.example.filedemo.model;

import java.util.HashMap;
import java.util.List;

public class OutputContent_1 {
	private String inputRecordId;
//	private List<HashMap<String, Object>> matchedAttributes;
	private List<HashMap<String, Object>> matchedUris;
	private List<HashMap<String, Object>> matchedEntities;

	public List<HashMap<String, Object>> getMatchedEntities() {
		return matchedEntities;
	}

	public void setMatchedEntities(List<HashMap<String, Object>> matchedEntities) {
		this.matchedEntities = matchedEntities;
	}

	private String fullName;
	private String lastName;

	public String getInputRecordId() {
		return inputRecordId;
	}

	public void setInputRecordId(String inputRecordId) {
		this.inputRecordId = inputRecordId;
	}

	public List<HashMap<String, Object>> getMatchedUris() {
		return matchedUris;
	}

	public void setMatchedUris(List<HashMap<String, Object>> matchedUris) {
		this.matchedUris = matchedUris;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

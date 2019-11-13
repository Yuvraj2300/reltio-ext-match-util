package com.example.filedemo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class OutputContents {

	String uriSource;
	String matchRule;
	String ruleType;

	public String getUriSource() {
		return uriSource;
	}

	public void setUriSource(String uriSource) {
		this.uriSource = uriSource;
	}

	public String getMatchRule() {
		return matchRule;
	}

	public void setMatchRule(String matchRule) {
		this.matchRule = matchRule;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ArrayList<HashMap<String, Object>> getUrisMatched() {
		return urisMatched;
	}

	public void setUrisMatched(ArrayList<HashMap<String, Object>> urisMatched) {
		this.urisMatched = urisMatched;
	}

	String action;
	ArrayList<HashMap<String, Object>> urisMatched;
}

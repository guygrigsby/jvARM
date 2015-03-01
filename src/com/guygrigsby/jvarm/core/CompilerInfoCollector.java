package com.guygrigsby.jvarm.core;

import java.util.ArrayList;
import java.util.List;

public class CompilerInfoCollector {
	
	private List<CompilerError> errors;
	
	public CompilerInfoCollector() {
		errors = new ArrayList<CompilerError>();
	}

	public boolean hasErrors() {
		return errors.size() != 0;
	}
	
	public void addError(CompilerError error) {
		errors.add(error);
	}
	
	public String getErrorsAsString() {
		StringBuilder sb = new StringBuilder();
		for (CompilerError error : errors) {
			sb.append(error.toString() + "\n");
		}
		return sb.toString();
	}

}

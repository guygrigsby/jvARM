package com.guygrigsby.jvarm.core;

public class NullInfoCollector implements InfoCollector {

	@Override
	public boolean hasErrors() {
		return false;
	}

	@Override
	public void addError(CompilerError error) {

	}

	@Override
	public String getErrorsAsString() {
		return "";
	}

}

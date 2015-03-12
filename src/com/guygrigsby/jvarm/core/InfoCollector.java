package com.guygrigsby.jvarm.core;

public interface InfoCollector {

	public abstract boolean hasErrors();

	public abstract void addError(CompilerError error);

	public abstract String getErrorsAsString();

}
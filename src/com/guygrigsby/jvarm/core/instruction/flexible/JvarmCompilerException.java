package com.guygrigsby.jvarm.core.instruction.flexible;

public class JvarmCompilerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -627176986585304141L;
	private int lineNumber;

	public JvarmCompilerException(String string) {
		// TODO Auto-generated constructor stub
	}

	public void setLineNumber(int currentLineNumber) {
		lineNumber = currentLineNumber;
	}
	@Override
	public String getMessage() {
		return super.getMessage() + " : Line " + lineNumber;
		
	}

}

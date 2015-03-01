package com.guygrigsby.jvarm.core;

public class CompilerError extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5623904406537614930L;


	private static final int UNKNOWN_ERROR = 0;
	
	
	private int lineNumber;
	private int errorType;
	private String errorMessage;
	private String failedSourceCode;

	public CompilerError(int lineNumberIn, int errorTypeIn, String errorMessageIn,
			String failedSourceCodeIn) {
		super(errorMessageIn + ": line " + lineNumberIn);
		this.lineNumber = lineNumberIn;
		this.errorType = errorTypeIn;
		this.errorMessage = errorMessageIn;
		this.failedSourceCode = failedSourceCodeIn;
	}

	public CompilerError(int lineNumberIn, String errorMessageIn,
			String failedSourceCodeIn) {
		this(lineNumberIn, UNKNOWN_ERROR, errorMessageIn, failedSourceCodeIn);
	}
	
	public CompilerError(String errorMessageIn, String failedSourceCodeIn) {
		this(-1, errorMessageIn, failedSourceCodeIn);
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber
	 *            the lineNumber to set
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @return the errorType
	 */
	public int getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType
	 *            the errorType to set
	 */
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return the failedSourceCode
	 */
	public String getFailedSourceCode() {
		return failedSourceCode;
	}

	/**
	 * @param failedSourceCode the failedSourceCode to set
	 */
	public void setFailedSourceCode(String failedSourceCode) {
		this.failedSourceCode = failedSourceCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String toString() {
		return String.format("Line %s : %s : %s", lineNumber, failedSourceCode, errorMessage);
	}

}

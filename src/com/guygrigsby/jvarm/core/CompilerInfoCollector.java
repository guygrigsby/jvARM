package com.guygrigsby.jvarm.core;

import java.util.ArrayList;
import java.util.List;

public class CompilerInfoCollector implements InfoCollector {
	
	private List<CompilerError> errors;
	
	public CompilerInfoCollector() {
		errors = new ArrayList<CompilerError>();
	}

	/* (non-Javadoc)
	 * @see com.guygrigsby.jvarm.core.InfoCollector#hasErrors()
	 */
	@Override
	public boolean hasErrors() {
		return errors.size() != 0;
	}
	
	/* (non-Javadoc)
	 * @see com.guygrigsby.jvarm.core.InfoCollector#addError(com.guygrigsby.jvarm.core.CompilerError)
	 */
	@Override
	public void addError(CompilerError error) {
		errors.add(error);
	}
	
	/* (non-Javadoc)
	 * @see com.guygrigsby.jvarm.core.InfoCollector#getErrorsAsString()
	 */
	@Override
	public String getErrorsAsString() {
		StringBuilder sb = new StringBuilder();
		for (CompilerError error : errors) {
			sb.append(error.toString() + "\n");
		}
		return sb.toString();
	}

}

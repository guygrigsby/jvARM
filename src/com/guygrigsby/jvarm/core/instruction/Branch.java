package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public class Branch extends Instruction {
	
	private String conditionalCode;
	
	private String branchLabel;
	
	private Instruction branch;
	
	public Branch(String condition) {
		conditionalCode = condition;
	}

	@Override
	public int execute(Registers registers, Map<String, Instruction> labels) {
		Instruction possibleBranch = labels.get(branchLabel);
		if (conditionalCode.isEmpty()) {
			branch = possibleBranch;
		} else {
			branch = new Conditional(conditionalCode).execute(registers, possibleBranch);
		}
		return 0;
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException,
			CompilerError {
		branchLabel = (String) tokenizer.nextToken().value;
		if (!validLabel(branchLabel)) {
			int lineNo = tokenizer.getLineNumber();
			String line = tokenizer.advanceToNextLine();
			CompilerError error = new CompilerError(lineNo,
					"Invalid Label " + branchLabel,
					line);
			
			throw error;
		}

	}
	
	@Override
	public Instruction getNext() {
		if (branch != null ) {
			return branch;
		}
		return super.getNext();
	}

	private boolean validLabel(String label) {
		//TODO need a way to check for valid labels
		return true;
	}

}

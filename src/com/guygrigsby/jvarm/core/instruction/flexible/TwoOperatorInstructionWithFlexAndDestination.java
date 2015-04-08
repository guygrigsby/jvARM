package com.guygrigsby.jvarm.core.instruction.flexible;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public abstract class TwoOperatorInstructionWithFlexAndDestination extends Instruction {

	private String destOperand;
	private Instruction flexibleSecondOp;

	public TwoOperatorInstructionWithFlexAndDestination() {
		super();
	}

	@Override
	public final void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError {
		destOperand = (String) tokenizer.nextToken().value;
		if (!validRegister(destOperand)) {
			int lineNo = tokenizer.getLineNumber();
			String line = tokenizer.advanceToNextLine();
			CompilerError error = new CompilerError(lineNo,
					"Invalid Register " + destOperand,
					line);
			
			throw error;
		}
		Token comma = tokenizer.nextToken();
		flexibleSecondOp = new FlexibleSecondOperand();
		flexibleSecondOp.parse(tokenizer);
	}

	public String getDestRegister() {
		return destOperand;
	}

	public Instruction getFlexibleSecondOp() {
		return flexibleSecondOp;
	}

}

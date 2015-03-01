package com.guygrigsby.jvarm.core.instruction.flexible;

import java.io.IOException;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public abstract class InstructionWithFlex extends Instruction {
	
	private String destRegister;
	private String firstOperand;
	private Instruction flexibleSecondOp;

	public InstructionWithFlex() {
		super();
	}

	@Override
	public final void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError {
		destRegister = (String) tokenizer.nextToken().value;
		if (!validRegister(destRegister)) {
			int lineNo = tokenizer.getLineNumber();
			String line = tokenizer.advanceToNextLine();
			CompilerError error = new CompilerError(lineNo,
					"Invalid Register " + destRegister,
					line);
			
			throw error;
		}
		Token firstComma = tokenizer.nextToken();
		firstOperand = (String) tokenizer.nextToken().value;
		if (!validRegister(firstOperand)) {
			int lineNo = tokenizer.getLineNumber();
			String line = tokenizer.advanceToNextLine();
			CompilerError error = new CompilerError(lineNo,
					"Invalid Register " + firstOperand,
					line);
			
			throw error;
		}
		Token secondComma = tokenizer.nextToken();
		flexibleSecondOp = new FlexibleSecondOperand();
		flexibleSecondOp.parse(tokenizer);
	}

	public String getDestRegister() {
		return destRegister;
	}

	public String getFirstOperand() {
		return firstOperand;
	}

	public Instruction getFlexibleSecondOp() {
		return flexibleSecondOp;
	}

}

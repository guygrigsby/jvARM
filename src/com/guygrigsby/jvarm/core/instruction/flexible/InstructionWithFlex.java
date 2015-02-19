package com.guygrigsby.jvarm.core.instruction.flexible;

import java.io.IOException;

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
	public final void parse(ArmSourceTokenizer tokenizer) throws IOException {
		destRegister = (String) tokenizer.nextToken().value;
		Token firstComma = tokenizer.nextToken();
		firstOperand = (String) tokenizer.nextToken().value;
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

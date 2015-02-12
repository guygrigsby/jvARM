package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.instruction.flexible.FlexibleSecondOperand;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public abstract class ArithmeticInstruction extends Instruction {
	
	private String destRegister;
	private String firstOperand;
	private Instruction flexibleSecondOp;

	public ArithmeticInstruction() {
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
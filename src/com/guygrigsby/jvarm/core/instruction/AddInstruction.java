package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public class AddInstruction extends Instruction {
	
	private Instruction flexibleSecondOp;

	@Override
	public void execute(Map<String, Integer> registers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException {
		Token add = tokenizer.nextToken();
		Token firstRegister = tokenizer.nextToken();
		Token firstComma = tokenizer.nextToken();
		Token secondRegister = tokenizer.nextToken();
		Token secondComma = tokenizer.nextToken();
		flexibleSecondOp = new FlexibleSecondOperand();
		flexibleSecondOp.parse(tokenizer);
		
	}

}

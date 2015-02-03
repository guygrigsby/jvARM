package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public class AddInstruction extends Instruction {
	
	private String destRegister;
	private String firstOperand;
	
	private Instruction flexibleSecondOp;

	@Override
	public int execute(Map<String, Integer> registers) {
		int first = registers.get(firstOperand);
		int result = first + flexibleSecondOp.execute(registers);
		registers.put(destRegister, result);
		return result;
		

	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException {
		destRegister = (String) tokenizer.nextToken().value;
		Token firstComma = tokenizer.nextToken();
		firstOperand = (String) tokenizer.nextToken().value;
		Token secondComma = tokenizer.nextToken();
		flexibleSecondOp = new FlexibleSecondOperand();
		flexibleSecondOp.parse(tokenizer);
		
	}

}

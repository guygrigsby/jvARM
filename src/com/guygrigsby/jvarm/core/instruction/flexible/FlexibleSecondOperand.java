package com.guygrigsby.jvarm.core.instruction.flexible;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.guygrigsby.jvarm.core.instruction.Constant;
import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.instruction.RegisterContents;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public class FlexibleSecondOperand extends Instruction {
	
	Instruction instruction;

	@Override
	public int execute(Map<String, Integer> registers) {
		return instruction.execute(registers);
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException {
		Token token = tokenizer.nextToken();

		int tokenType = token.type;
		String tokenVal = token.value;
		
		switch (tokenType) {
		case ArmSourceTokenizer.REGISTER:
			Token next = tokenizer.nextToken();
			if (next.value.equals(",")) {
				instruction = new ShiftedRegister(tokenVal);
			} else {
				instruction = new RegisterContents(tokenVal);
			}
			
			break;
		case ArmSourceTokenizer.CONSTANT_MARKER:
			int intValue = token.intValue;
			instruction = new Constant();
			break;
		default:

			break;
		}
		
		instruction.parse(tokenizer);
		while (token.type != 1 && token.type != 0) {
			token = tokenizer.nextToken();
		}
		
	}

}

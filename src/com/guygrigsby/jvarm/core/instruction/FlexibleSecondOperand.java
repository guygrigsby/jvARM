package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		List<Token> tokens = new ArrayList<Token>();
		Token token = tokenizer.nextToken();
		while (token.type != 1 && token.type != 0) {
			tokens.add(token);
			token = tokenizer.nextToken();
		}
		switch (tokens.size()) {
		case 1:
			int tokenType = tokens.get(0).type;
			String tokenVal = tokens.get(0).value;
			if (tokenType == ArmSourceTokenizer.REGISTER) {
				instruction = new RegisterContents(tokenVal);
			}
			break;
		case 2:
			tokenType = tokens.get(1).type;
			int intValue = tokens.get(1).intValue;
			if (tokenType == ArmSourceTokenizer.CONSTANT) {
				instruction = new Constant(intValue);
			}
			break;
		default:
			
			break;
		}
		
		instruction.parse(tokenizer);
		// TODO Auto-generated method stub
		
	}

}

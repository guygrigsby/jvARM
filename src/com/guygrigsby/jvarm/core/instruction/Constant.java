package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public class Constant extends Instruction {
	
	int constant;

	@Override
	public int execute(Registers registers, Map<String, Instruction> labels) {
		return constant;
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError {
		Token number = tokenizer.nextToken();
		//TODO need to check that the number is 
		//an 8-bit pattern rotated by an even number of bits within a 32-bit word
		constant = number.intValue;
	}

}

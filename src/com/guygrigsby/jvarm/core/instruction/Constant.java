package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public class Constant extends Instruction {
	
	int constant;

	public Constant(int number) {
		//TODO need to check that the number is 
		//an 8-bit pattern rotated by an even number of bits within a 32-bit word
		constant = number;
	}

	@Override
	public int execute(Map<String, Integer> registers) {
		return constant;
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException {
		return;
	}

}

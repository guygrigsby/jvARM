package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public class RegisterContents extends Instruction {

	private String register;

	public RegisterContents(String reg) {
		register = reg;
	}

	@Override
	public int execute(Map<String, Integer> registers) {
		return registers.get(register);
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException {
		return;
	}

}

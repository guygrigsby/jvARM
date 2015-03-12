package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public class RegisterContents extends Instruction {

	private String register;

	public RegisterContents(String reg) {
		register = reg;
	}

	@Override
	public int execute(Registers registers) {
		return registers.get(register);
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError {
		if (!validRegister(register)) {
			int lineNo = tokenizer.getLineNumber();
			String line = tokenizer.advanceToNextLine();
			CompilerError error = new CompilerError(lineNo,
					"Invalid Register " + register,
					line);
			
			throw error;
		}
		return;
	}
	
	public String getRegisterName() {
		return register;
	}

}

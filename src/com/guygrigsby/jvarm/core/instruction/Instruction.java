package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public abstract class Instruction {
	private Instruction next;
	
	public void setNext(Instruction nextNode) {
		next = nextNode;
	}
	
	public Instruction getNext() {
		return next;
	}
	
	public abstract int execute(Map<String, Integer> registers);
	
	public abstract void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError;
	

	protected boolean validRegister(String reg) {
		try {
			Integer num = Integer.parseInt(reg.substring(1));
			return reg.startsWith("R") && num >= 0 && num < 13;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}
}

package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public abstract class Instruction {
	
	private int lineNumber;
	private String label;
	private Instruction next;
	
	public void setNext(Instruction nextNode) {
		next = nextNode;
	}
	
	public void setLabel(String labelIn) {
		label = labelIn;
	}
	
	public Instruction getNext() {
		return next;
	}
	
	public abstract int execute(Registers registers, Map<String, Instruction> labels);
	
	public abstract void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError;
	

	protected boolean validRegister(String reg) {
		try {
			Integer num = Integer.parseInt(reg.substring(1));
			return reg.startsWith("R") && num >= 0 && num < 13;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}

	public String getLabel() {
		return label;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNo) {
		lineNumber = lineNo;
	}
}

package com.guygrigsby.jvarm.core.instruction;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public abstract class Instruction {
	private Instruction next;
	
	public void setNext(Instruction nextNode) {
		next = nextNode;
	}
	
	public Instruction getNext() {
		return next;
	}
	
	public abstract void execute(Map<String, Integer> registers);
	
	public abstract void parse(ArmSourceTokenizer tokenizer) throws IOException;
}

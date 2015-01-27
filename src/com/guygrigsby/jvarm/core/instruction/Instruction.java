package com.guygrigsby.jvarm.core.instruction;

public abstract class Instruction {
	private Instruction next;
	
	public void setNext(Instruction nextNode) {
		next = nextNode;
	}
	
	public Instruction getNext() {
		return next;
	}
}

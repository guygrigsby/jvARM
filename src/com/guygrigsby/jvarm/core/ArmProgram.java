package com.guygrigsby.jvarm.core;

import com.guygrigsby.jvarm.core.instruction.Instruction;

public class ArmProgram {
	
	private Instruction current;
	/**
	 * Creates a new program
	 * @param rootIn the root of a tree of {@code Instruction}s
	 */
	public ArmProgram(Instruction root) {
		current = root;
	}
	
	public void run(Registers registers, Memory memory) {
		while (current != null) {
			step(registers);
		}
	}
	
	public void step(Registers registers) {
		current.execute(registers);
		current = current.getNext();
	}

}

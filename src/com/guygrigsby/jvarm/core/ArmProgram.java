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
	
	public void run(ARMProcessor cpu, Memory memory) {
		while (current.getNext() != null) {
			step(cpu, memory);
		}
	}
	
	public void step(ARMProcessor cpu, Memory memory) {
		
		current = current.getNext();
	}

}

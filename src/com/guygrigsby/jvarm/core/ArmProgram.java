package com.guygrigsby.jvarm.core;

import java.util.Map;

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
	
	public void run(Map<String, Integer> registers) {
		while (current != null) {
			step(registers);
		}
	}
	
	public void step(Map<String, Integer> registers) {
		current.execute(registers);
		current = current.getNext();
	}

}

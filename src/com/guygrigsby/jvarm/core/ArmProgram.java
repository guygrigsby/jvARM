package com.guygrigsby.jvarm.core;

import java.util.Map;

import com.guygrigsby.jvarm.core.instruction.Instruction;

public class ArmProgram {
	
	private Instruction current;
	private Map<String, Instruction> labels;
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
		current.execute(registers, labels);
		current = current.getNext();
	}

	public void setLabels(Map<String, Instruction> labelsIn) {
		labels = labelsIn;
		
	}

	/**
	 * @return the labels
	 */
	public Map<String, Instruction> getLabels() {
		return labels;
	}
	
	public int getLineUnderExecution() {
		return current.getLineNumber();
	}

}

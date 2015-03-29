package com.guygrigsby.jvarm.core.instruction;


import com.guygrigsby.jvarm.core.Registers;
/**
 * Represents the conditional addition to those instructions that support it
 * eg in the instruction {@code BEQ label}, the EQ is the conditional part
 * @author guy
 *
 */
public class Conditional {
	
	private String conditionCode;
	
	public Conditional(String condition) {
		conditionCode = condition;
	}

	public Instruction execute(Registers registers, Instruction possibleBranch) {
		Instruction nextInstruction = null;
		switch (conditionCode) {
		case "EQ":
			if (registers.isZero()) {
				nextInstruction = possibleBranch;
			}
			break;
		case "NE":
			if (!registers.isZero()) {
				nextInstruction = possibleBranch;
			}
			break;
		}
		return nextInstruction;
	}

}

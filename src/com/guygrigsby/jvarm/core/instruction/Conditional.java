package com.guygrigsby.jvarm.core.instruction;


import com.guygrigsby.jvarm.core.Registers;
/**
 * Represents the conditional addition to those instructions that support it
 * eg in the instruction {@code BEQ label}, the EQ is the conditional part
 * @author guy
 *
 */
public class Conditional {
	
	private static final String EQ = "EQ";
	private static final String NE = "NE";
	private static final String CS = "CS";
	private static final String CC = "CC";
	private static final String MI = "MI";
	private static final String PL = "PL";
	private static final String VS = "VS";
	private static final String VC = "VC";
	private static final String HI = "HI";
	private static final String LS = "LS";
	private static final String GE = "GE";
	private static final String LT = "LT";
	private static final String GT = "GT";
	private static final String LE = "LE";
	private static final String AL = "AL";
	
	private String conditionCode;
	
	public Conditional(String condition) {
		conditionCode = condition;
	}

	public Instruction execute(Registers registers, Instruction possibleBranch) {
		Instruction nextInstruction = null;
		switch (conditionCode) {
		case EQ:
			if (registers.isZero()) {
				nextInstruction = possibleBranch;
			}
			break;
		case NE:
			if (!registers.isZero()) {
				nextInstruction = possibleBranch;
			}
			break;
		case CS:
			if (registers.isCarry()) {
				nextInstruction = possibleBranch;
			}
			break;
		case CC:
			if (!registers.isCarry()) {
				nextInstruction = possibleBranch;
			}
			break;
		case MI:
			if (registers.isNegative()) {
				nextInstruction = possibleBranch;
			}
			break;
		case PL:
			if (!registers.isNegative()) {
				nextInstruction = possibleBranch;
			}
			break;
		case VS:
			if (registers.isOverflow()) {
				nextInstruction = possibleBranch;
			}
			break;
		case VC:
			if (!registers.isOverflow()) {
				nextInstruction = possibleBranch;
			}
			break;
		case HI:
			if (registers.isCarry() && !registers.isZero()) {
				nextInstruction = possibleBranch;
			}
			break;
		case LS:
			if (!registers.isCarry() || registers.isZero()) {
				nextInstruction = possibleBranch;
			}
			break;
		case GE:
			if (registers.isNegative() == registers.isOverflow()) {
				nextInstruction = possibleBranch;
			}
			break;
		case LT:
			if (registers.isNegative() != registers.isOverflow()) {
				nextInstruction = possibleBranch;
			}
			break;
		case GT:
			if (!registers.isZero() && (registers.isNegative() == registers.isOverflow())) {
				nextInstruction = possibleBranch;
			}
			break;
		case LE:
			if (registers.isZero() && (registers.isNegative() != registers.isOverflow())) {
				nextInstruction = possibleBranch;
			}
			break;
		case AL:
			nextInstruction = possibleBranch;
		}
		return nextInstruction;
	}

}

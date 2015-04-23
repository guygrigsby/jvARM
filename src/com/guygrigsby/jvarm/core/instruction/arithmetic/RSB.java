package com.guygrigsby.jvarm.core.instruction.arithmetic;

import java.util.Map;

import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.instruction.Instruction;

public class RSB extends ArithmeticInstruction {

	@Override
	public int execute(Registers registers, Map<String, Instruction> labels) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers, labels);
		int result;
		try {
			result = Math.subtractExact(second, first);
			registers.setOverflow(false);
		} catch (ArithmeticException e) {
			result = second - first;
			registers.setOverflow(true);
		}
		registers.put(getDestRegister(), result);
		return result;
	}

	@Override
	public String getInstructionName() {
		return "RSB";
	}

}

package com.guygrigsby.jvarm.core.instruction.arithmetic;

import java.util.Map;

public class RSB extends ArithmeticInstruction {

	@Override
	public int execute(Map<String, Integer> registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);;
		int result = second - first;
		registers.put(getDestRegister(), result);
		return result;
	}

}

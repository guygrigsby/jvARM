package com.guygrigsby.jvarm.core.instruction;

import java.util.Map;

public class RSBInstruction extends ArithmeticInstruction {

	@Override
	public int execute(Map<String, Integer> registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);;
		int result = second - first;
		registers.put(getDestRegister(), result);
		return result;
	}

}

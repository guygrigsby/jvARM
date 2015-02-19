package com.guygrigsby.jvarm.core.instruction.arithmetic;

import java.util.Map;

public class ADD extends ArithmeticInstruction {

	@Override
	public int execute(Map<String, Integer> registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);;
		int result = first + second;
		registers.put(getDestRegister(), result);
		return result;
	}



}

package com.guygrigsby.jvarm.core.instruction.logical;

import java.util.Map;

public class OR extends LogicalInstruction {

	@Override
	public int execute(Map<String, Integer> registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);;
		int result = first | second;
		registers.put(getDestRegister(), result);
		return result;
	}

}

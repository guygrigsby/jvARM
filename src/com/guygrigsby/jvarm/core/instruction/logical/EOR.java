package com.guygrigsby.jvarm.core.instruction.logical;

import com.guygrigsby.jvarm.core.Registers;

public class EOR extends LogicalInstruction {

	@Override
	public int execute(Registers registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);;
		int result = first ^ second;
		registers.put(getDestRegister(), result);
		return result;
	}

}

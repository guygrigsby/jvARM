package com.guygrigsby.jvarm.core.instruction.arithmetic;

import com.guygrigsby.jvarm.core.Registers;

public class RSB extends ArithmeticInstruction {

	@Override
	public int execute(Registers registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);;
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

}

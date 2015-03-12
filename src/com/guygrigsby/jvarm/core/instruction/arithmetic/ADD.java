package com.guygrigsby.jvarm.core.instruction.arithmetic;

import com.guygrigsby.jvarm.core.Registers;

public class ADD extends ArithmeticInstruction {

	@Override
	public int execute(Registers registers) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers);
		int result;
		try {
			result = Math.addExact(first, second);//first + second;
			registers.setOverflow(false);
		} catch (ArithmeticException e) {
			result = first + second;
			registers.setOverflow(true);
		}
		registers.put(getDestRegister(), result);
		return result;
	}



}

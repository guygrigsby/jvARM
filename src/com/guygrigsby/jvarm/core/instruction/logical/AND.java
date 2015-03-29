package com.guygrigsby.jvarm.core.instruction.logical;

import java.util.Map;

import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.instruction.Instruction;

public class AND extends LogicalInstruction {

	@Override
	public int execute(Registers registers, Map<String, Instruction> labels) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers, labels);
		int result = first & second;
		registers.put(getDestRegister(), result);
		return result;
	}

}

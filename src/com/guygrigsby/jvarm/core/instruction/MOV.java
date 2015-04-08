package com.guygrigsby.jvarm.core.instruction;

import java.util.Map;

import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.instruction.flexible.TwoOperatorInstructionWithFlexAndDestination;

public class MOV extends TwoOperatorInstructionWithFlexAndDestination {

	public MOV() {
		super();
	}

	@Override
	public int execute(Registers registers, Map<String, Instruction> labels) {
		int toMove = getFlexibleSecondOp().execute(registers, labels);
		registers.put(getDestRegister(), toMove);
		return toMove;
	}

}

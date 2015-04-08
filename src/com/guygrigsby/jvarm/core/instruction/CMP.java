/**
 * 
 */
package com.guygrigsby.jvarm.core.instruction;

import java.util.Map;

import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.instruction.flexible.TwoOperatorInstructionWithFlexWithoutDestination;

/**
 * @author guy
 *
 */
public class CMP extends TwoOperatorInstructionWithFlexWithoutDestination {

	/**
	 * 
	 */
	public CMP() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.guygrigsby.jvarm.core.instruction.Instruction#execute(com.guygrigsby.jvarm.core.Registers, java.util.Map)
	 */
	@Override
	public int execute(Registers registers, Map<String, Instruction> labels) {
		int first = registers.get(getFirstOperand());
		int second = getFlexibleSecondOp().execute(registers, labels);
		int result;
		try {
			result = Math.subtractExact(first, second);
			registers.setOverflow(false);
		} catch (ArithmeticException e) {
			result = first - second;
			registers.setOverflow(true);
		}
		if (result < 0) {
			registers.setNegative(true);
			registers.setZero(false);
		} else if (result == 0) {
			registers.setNegative(false);
			registers.setZero(true);
		} else {
			registers.setNegative(false);
			registers.setZero(false);
		}
		/*TODO update carry is set to false 
		 * if the subtraction produced a borrow (that is, an unsigned underflow)
		 */
		return result;
	}
}

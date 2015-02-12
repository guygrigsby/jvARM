package com.guygrigsby.jvarm.core.instruction.flexible;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.instruction.Constant;
import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.instruction.RegisterContents;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public class ShiftedRegister extends RegisterContents {
	
	private String shiftType;
	private Instruction shiftByInstruction;

	public ShiftedRegister(String register) {
		super(register);
	}

	@Override
	public int execute(Map<String, Integer> registers) {
		int valueToBeShifted = super.execute(registers);
		int valueToShiftBy = shiftByInstruction.execute(registers);
		int shiftedValue = 0;
		switch (shiftType) {
		case "ASR":
			shiftedValue = valueToBeShifted >> valueToShiftBy;
			break;
		case "LSL":
			shiftedValue = valueToBeShifted << valueToShiftBy;
			break;
		case "LSR":
			shiftedValue = valueToBeShifted >>> valueToShiftBy;
			break;
		case "ROR":
			Integer.rotateRight(valueToBeShifted, valueToShiftBy);
			break;
		case "RRX":
			shiftedValue = valueToBeShifted >> 1;
			break;
		default:
			//TODO error
			throw new RuntimeException("Invalid Shift operation in FLexible second operand");
		}
		return shiftedValue;
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException {
		Token shift = tokenizer.nextToken();
		shiftType = shift.value;
		Token shiftBy = tokenizer.nextToken();;
		int shiftByType= shiftBy.type;// register or constant
		if (shiftByType == ArmSourceTokenizer.CONSTANT) {
			shiftByInstruction = new Constant();
		} else if (shiftByType == ArmSourceTokenizer.REGISTER) {
			shiftByInstruction = new RegisterContents(shiftBy.value);
		} else {
			//TODO ERROR
		}
		shiftByInstruction.parse(tokenizer);
		
	}

}

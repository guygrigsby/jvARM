package com.guygrigsby.jvarm.core.instruction.flexible;

import java.io.IOException;
import java.util.Map;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.Registers;
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
	public int execute(Registers registers, Map<String, Instruction> labels) {
		int valueToBeShifted = super.execute(registers, labels);
		int valueToShiftBy = shiftByInstruction.execute(registers, labels);
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
			shiftedValue = Integer.rotateRight(valueToBeShifted, valueToShiftBy);
			boolean carry = (shiftedValue & 0x80000000) == 0x80000000;
			registers.setCarry(carry);
			break;
		case "RRX":
			boolean currentCarry = registers.isCarry();
			boolean newCarry = (valueToBeShifted & 0x1) == 0x1;
			shiftedValue = valueToBeShifted >> 1;
			if (currentCarry) {
				shiftedValue = shiftedValue | 0x80000000;
			}
			registers.setCarry(newCarry);
			break;
		case "":
			break;
		default:
			//TODO error
			throw new RuntimeException("Invalid Shift operation in FLexible second operand");
		}
		return shiftedValue;
	}

	@Override
	public void parse(ArmSourceTokenizer tokenizer) throws IOException, CompilerError {
		Token shift = tokenizer.nextToken();
		shiftType = shift.value;
		Token shiftBy = tokenizer.nextToken();;
		int shiftByType= shiftBy.type;// register or constant
		if (shiftByType == ArmSourceTokenizer.CONSTANT) {
			shiftByInstruction = new Constant();
		} else if (shiftByType == ArmSourceTokenizer.REGISTER) {
			shiftByInstruction = new RegisterContents(shiftBy.value);
		} else {
			int lineNo = tokenizer.getLineNumber();
			String line = tokenizer.advanceToNextLine();
			CompilerError error = new CompilerError(lineNo,
					"Shifted Register must be shifted by valid constant or register contents",
					line);
			
			throw error;
		}
		shiftByInstruction.parse(tokenizer);
		
	}

}

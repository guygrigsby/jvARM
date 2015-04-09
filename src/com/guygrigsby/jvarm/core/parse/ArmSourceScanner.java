package com.guygrigsby.jvarm.core.parse;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guygrigsby.jvarm.core.CompilerError;
import com.guygrigsby.jvarm.core.instruction.Branch;
import com.guygrigsby.jvarm.core.instruction.CMP;
import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.instruction.MOV;
import com.guygrigsby.jvarm.core.instruction.arithmetic.ADD;
import com.guygrigsby.jvarm.core.instruction.arithmetic.RSB;
import com.guygrigsby.jvarm.core.instruction.arithmetic.SUB;
import com.guygrigsby.jvarm.core.instruction.logical.AND;
import com.guygrigsby.jvarm.core.instruction.logical.EOR;
import com.guygrigsby.jvarm.core.instruction.logical.OR;
/**
 * This class taken an input stream for a ARM source code and parses it
 * into {@code Instruction}s
 * @author guy
 *
 */
public class ArmSourceScanner {
	
	private static int EOF = 0;

	private static final Logger logger = LogManager.getLogger();
	
	private ArmSourceTokenizer tokenizer;

	private Token current;

	
	public ArmSourceScanner(InputStream source) {
		tokenizer = new ArmSourceTokenizer(source);
		try {
			current = tokenizer.nextToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Returns the next instruction in the source and advances the scanner.
	 * There is no going back.
	 * @return the next instruction or null if there is none.
	 */
	public Instruction nextInstruction() throws CompilerError {
		Instruction instruction = null;
		String labelName = null;
		switch (current.type) {
		case ArmSourceTokenizer.ADD:
			instruction = new ADD();
			break;
		case ArmSourceTokenizer.SUB:
			instruction = new SUB();
			break;
		case ArmSourceTokenizer.RSB:
			instruction = new RSB();
			break;
		case ArmSourceTokenizer.AND:
			instruction = new AND();
			break;
		case ArmSourceTokenizer.OR:
			instruction = new OR();
			break;
		case ArmSourceTokenizer.EOR:
			instruction = new EOR();
			break;
		case ArmSourceTokenizer.BRANCH:
			instruction = new Branch(current.value.substring(1));
			break;
		case ArmSourceTokenizer.CMP:
			instruction = new CMP();
			break;
		case ArmSourceTokenizer.MOV:
			instruction = new MOV();
			break;
		case ArmSourceTokenizer.EOF:
			return null;
		case ArmSourceTokenizer.LABEL:
			labelName = current.value;
			tokenizer.advanceToNextLine();
			try {
				current = tokenizer.nextToken();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			instruction = nextInstruction();
			instruction.setLabel(labelName);
			return instruction;
		default:
			int lineNo = tokenizer.getLineNumber();
			String instructionName = current.value;
			String badLine = tokenizer.advanceToNextLine();
			try {
				current = tokenizer.nextToken();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new CompilerError(lineNo, "Unrecognized Instruction " + instructionName, badLine);
		}
		if (instruction != null) {
			try {
				int lineNo = tokenizer.getLineNumber();
				instruction.parse(tokenizer);
				instruction.setLineNumber(lineNo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CompilerError e) {
				//e.setLineNumber(tokenizer.getLineNumber());
				throw e;
			}
		}
		try {
			current = tokenizer.nextToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instruction;
	}
}

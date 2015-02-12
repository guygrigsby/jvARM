package com.guygrigsby.jvarm.core.parse;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guygrigsby.jvarm.core.instruction.AddInstruction;
import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.instruction.RSBInstruction;
import com.guygrigsby.jvarm.core.instruction.SubInstruction;
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
	public Instruction nextInstruction() {
		Instruction instruction = null;
		switch (current.type) {
		case ArmSourceTokenizer.ADD:
			instruction = new AddInstruction();
			break;
		case ArmSourceTokenizer.SUB:
			instruction = new SubInstruction();
			break;
		case ArmSourceTokenizer.RSB:
			instruction = new RSBInstruction();
			break;
		}
		if (instruction != null) {
			try {
				instruction.parse(tokenizer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

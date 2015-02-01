package com.guygrigsby.jvarm.core;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guygrigsby.jvarm.core.instruction.Instruction;
import com.guygrigsby.jvarm.core.parse.ArmSourceScanner;

public class ArmSourceCompiler {
	
	private static final Logger logger = LogManager.getLogger();
	
	public ArmSourceCompiler() {
		
	}
	/**
	 * 
	 * @param source
	 * @return the root node of the program (aka the first instruction)
	 * @throws IOException
	 */
	public ArmProgram compile(InputStream source) {
		Instruction prev = null;
		Instruction next = null;
		ArmSourceScanner scanner = new ArmSourceScanner(source);
		next = scanner.nextInstruction();
		ArmProgram program = new ArmProgram(next);
		do {
			prev = next;
			next = scanner.nextInstruction();
			prev.setNext(next);
		} while (next != null);
		
		return program;
	}
}

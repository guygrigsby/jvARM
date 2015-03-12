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
	 * @return the root node of the program (aka the first instruction) or {@code null} id compiler errors
	 * @throws IOException
	 */
	public ArmProgram compile(InputStream source, InfoCollector collector ) {
		if (collector == null) {
			collector = new NullInfoCollector();
		}
		Instruction prev = null;
		Instruction next = null;
		ArmSourceScanner scanner = new ArmSourceScanner(source);
		try {
			next = scanner.nextInstruction();
		} catch (CompilerError e) {
			collector.addError(e);
		}
		ArmProgram program = new ArmProgram(next);
		do {
			prev = next;
			try {
				next = scanner.nextInstruction();
			} catch (CompilerError e) {
				next = null;
				collector.addError(e);
			}
			if (prev != null) {
				prev.setNext(next);
			}
		} while (next != null);
		
		return program;
	}
}

package com.guygrigsby.jvarm.core.parse;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guygrigsby.jvarm.core.Instruction;
import com.guygrigsby.jvarm.core.InstructionSet;

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
	
	public Instruction nextInstruction() {
		Instruction instruction = null;
		switch (current.type) {
		
		}
		return instruction;
	}
	

	private boolean isAddOrSubInstruction(Token token) {
		int type = InstructionSet.getInstance().getInstructionType(token);
		return type == InstructionSet.ADD || type == InstructionSet.SUBTRACT;
		
	}

}

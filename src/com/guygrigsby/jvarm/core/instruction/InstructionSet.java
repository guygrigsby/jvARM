package com.guygrigsby.jvarm.core.instruction;

import java.util.HashSet;
import java.util.Set;

import com.guygrigsby.jvarm.core.parse.Token;

public class InstructionSet {
	/*
	 * Types of instructions
	 */
	public static int UNKNOWN = 0;
	public static int ADD = 1;
	public static int SUBTRACT = 2;
	public static int SHIFT = 3;
	
	private static Set<String> addInstructions = new HashSet<String>();
	private static Set<String> subtractInstructions = new HashSet<String>();
	
	private static InstructionSet instance;
	
	private InstructionSet() {
		addInstructions.add("ADD");
		addInstructions.add("ADC");
		
		subtractInstructions.add("SUB");
		subtractInstructions.add("RSB");
		subtractInstructions.add("SBC");
		subtractInstructions.add("RSC");
		
	}
	
	public static InstructionSet getInstance() {
		if (instance == null) {
			instance = new InstructionSet();
		}
		return instance;
	}
	
	public int getInstructionType(Token token) {
		Object possibleInstruction = token.token;
		int type = UNKNOWN;
		if (addInstructions.contains(possibleInstruction)) {
			type = ADD;
		} else if (subtractInstructions.contains(possibleInstruction)) {
			type = SUBTRACT;
		}
		return type;
	}

}

package com.guygrigsby.jvarm.core.parse;

public class Token {
	
	public String value;
	public int type;
	/**
	 * Only non-zero if the type is {@code ArmSourceTokenizer.CONSTANT}
	 */
	public int intValue;
	
	public Token(String valueIn, int typeIn) {
		this(valueIn, 0, typeIn);
	}
	public Token(String valueIn, int intValueIn, int typeIn) {
		value = valueIn;
		type = typeIn;
		intValue = intValueIn;
	}
	
	public String toString() {
		return String.format("[%s %s]", value, type);
	}
}

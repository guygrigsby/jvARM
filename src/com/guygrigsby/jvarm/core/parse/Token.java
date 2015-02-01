package com.guygrigsby.jvarm.core.parse;

public class Token {
	
	public Object token;
	public int type;
	
	public Token(Object tokenIn, int typeIn) {
		token = tokenIn;
		type = typeIn;
	}
	
	public String toString() {
		return String.format("[%s %s]", token, type);
	}
}

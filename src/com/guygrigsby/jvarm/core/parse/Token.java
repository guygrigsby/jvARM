package com.guygrigsby.jvarm.core.parse;

public class Token {
	public Object token;
	public int number;
	
	public Token(Object tokenIn, int numberIn) {
		token = tokenIn;
		number = numberIn;
	}
	
	public String toString() {
		return String.format("[%s %s]", token, number);
	}
}

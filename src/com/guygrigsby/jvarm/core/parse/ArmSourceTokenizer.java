package com.guygrigsby.jvarm.core.parse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArmSourceTokenizer {

	private static final Logger logger = LogManager.getLogger();
	
	
	public static final int REGISTER 				= 2;
	public static final int FLEXIBLE_SECOND_OPERAND = 3;
	public static final int ADD 					= 4;
	public static final int SUB 					= 5;
	public static final int RSB 					= 6;
	public static final int AND						= 7;
	public static final int OR						= 8;
	public static final int EOR						= 9;
	public static final int LABEL					= 10;
	public static final int BRANCH					= 11;
	
	public static final int DEFAULT_TOKEN_NUMBER = 12;
	
	public static final int CONSTANT = 13;

	/**
	 * Represents the # that precedes a constant
	 */
	public static final int CONSTANT_MARKER = 14;


	public static final int EOF = 0;
	
	public Map<String, Integer> keywords; 

	private StreamTokenizer delegate;

	public ArmSourceTokenizer(InputStream inputIn) {
		keywords = buildKeywordMap();
		InputStreamReader reader = new InputStreamReader(inputIn);
		logger.trace("Encoding {}", reader.getEncoding());
		delegate = new StreamTokenizer(reader);
		delegate.commentChar('*');
		delegate.commentChar(';');
		delegate.parseNumbers();
		delegate.eolIsSignificant(true);
		delegate.lowerCaseMode(false);
	}

	private Map<String, Integer> buildKeywordMap() {
		keywords = new HashMap<String, Integer>();
		keywords.put("ADD", ADD);
		keywords.put("SUB", SUB);
		keywords.put("RSB", RSB);
		keywords.put("AND", AND);
		keywords.put("OR", OR);
		keywords.put("EOR", EOR);
		
		keywords.put("B", BRANCH);
		keywords.put("BEQ", BRANCH);
		keywords.put("BNE", BRANCH);
		
		//TODO add others
		return keywords;
	}
	
	public Token nextToken() throws IOException {
		int next = delegate.nextToken();
		String tokenValue = null;
		int tokenType = DEFAULT_TOKEN_NUMBER;
		int tokenIntValue = 0;
		switch (next) {

		case StreamTokenizer.TT_NUMBER:
			int num = (int) delegate.nval;
			if (num == 0) { //hex TODO this should be done by finding a #
				next = delegate.nextToken();
				String possibleHex = delegate.sval;
				if (possibleHex.startsWith("x")) {
					tokenValue = possibleHex.substring(1);
					tokenType = 13;
				} else {
					delegate.pushBack();
				}
			} else {
				tokenValue = num+""; //TODO this is a hack
				tokenIntValue = num;
				tokenType = 13;
			}
			break;

		case StreamTokenizer.TT_WORD:
			String word = delegate.sval.toUpperCase();
			if ((word.length() ==2 || word.length() == 3) && word.startsWith("R")) { //TODO this is ugly
				tokenType = REGISTER;
			} else {
				tokenType = (keywords.get(word) == null) ? LABEL : keywords.get(word);
			}
			tokenValue = word;
			break;

		case '"':

			tokenValue = delegate.sval;
			break;

		case '\'':
			tokenValue = delegate.sval;
			break;
		case StreamTokenizer.TT_EOL:
			tokenValue = "eol";
			tokenType = 1;
			break;
		case StreamTokenizer.TT_EOF:
			tokenValue = "eof";
			tokenType = EOF;
			break;
		default:
			char c = (char) delegate.ttype;
			switch (c) {
			
			case '#':
				tokenType = CONSTANT_MARKER;
			default:
				tokenValue = Character.toString(c);
			}

			break;
		}
		Token token = new Token(tokenValue, tokenIntValue, tokenType); 
		logger.trace(token);
		return token;
	}
	
	public String advanceToNextLine() {
		StringBuilder sb = new StringBuilder();
		try {
			int next = delegate.ttype; 
			while (next != StreamTokenizer.TT_EOL && next != StreamTokenizer.TT_EOF) {
				if (delegate.sval != null) {
					sb.append(delegate.sval);
				} else {
					sb.append((char)delegate.ttype);
				}
				
				sb.append(" ");
				next = delegate.nextToken(); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public int getLineNumber() {
		return delegate.lineno();
	}
}

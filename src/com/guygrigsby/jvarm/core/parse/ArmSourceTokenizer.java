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

	private static final Logger logger = LogManager
			.getLogger(ArmSourceTokenizer.class);

	private static final Integer DEFAULT_TOKEN_NUMBER = 12;
	
	private Map<String, Integer> keywords; 

	private StreamTokenizer delegate;

	public ArmSourceTokenizer(InputStream inputIn) {
		keywords = buildKeywordMap();
		InputStreamReader reader = new InputStreamReader(inputIn);
		logger.trace("Encoding " + reader.getEncoding());
		delegate = new StreamTokenizer(reader);
		delegate.commentChar('*');
		delegate.commentChar(';');
		delegate.parseNumbers();
		delegate.eolIsSignificant(true);
		delegate.lowerCaseMode(false);
	}

	private Map<String, Integer> buildKeywordMap() {
		keywords = new HashMap<String, Integer>();
		InputStream is = ArmSourceTokenizer.class.getResourceAsStream("Keywords.txt");
		Scanner in = new Scanner(is);
		while (in.hasNext()) {
			String next = in.next();
			if (next.equals("*")) {
				break;
			}
			keywords.put(next, in.nextInt());
		}
		in.close();
		return keywords;
	}
	
	public Token nextToken() throws IOException {
		int next = delegate.nextToken();
		Object tokenValue = null;
		int tokenType = DEFAULT_TOKEN_NUMBER;
		switch (next) {

		case StreamTokenizer.TT_NUMBER:
			double num = delegate.nval;
			if (num == 0) { //hex TODO this should be done by finding a #
				next = delegate.nextToken();
				String possibleHex = delegate.sval;
				if (possibleHex.startsWith("x")) {
					tokenValue = Integer.parseInt(possibleHex.substring(1), 16);
					tokenType = 13;
				} else {
					delegate.pushBack();
				}
			} else {
				tokenValue = delegate.nval;
				tokenType = 13;
			}
			break;

		case StreamTokenizer.TT_WORD:
			String word = delegate.sval.toUpperCase();
			if ((word.length() ==2 || word.length() == 3) && word.startsWith("r")) { //TODO this is ugly
				tokenType = 14;
			} else {
				tokenType = keywords.get(word) == null ? DEFAULT_TOKEN_NUMBER : keywords.get(word);
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
			tokenType = 0;
			break;
		default:
			char c = (char) delegate.ttype;
			switch (c) {
			
			case '#':
				
				//break;
			default:
				tokenValue = c;
			}

			break;
		}

		return new Token(tokenValue, tokenType);
	}
}

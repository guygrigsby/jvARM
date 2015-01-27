package com.guygrigsby.jvarm.core;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	public Node compile(InputStream source) {

		return null;
	}
}

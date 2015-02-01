package com.guygrigsby.jvarm.core.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.guygrigsby.jvarm.core.ArmProgram;
import com.guygrigsby.jvarm.core.ArmSourceCompiler;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;
import com.guygrigsby.jvarm.core.parse.Token;

public class ArmSourceTokenizerTest {
	
	private static final Logger logger = LogManager.getLogger(ArmSourceTokenizerTest.class);

	
	ArmSourceTokenizer tokenizer;

	@Before
	public void setUp() throws Exception {
		 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		
		InputStream is = ArmSourceTokenizerTest.class.getResourceAsStream("add.s");
		ArmProgram program = new ArmSourceCompiler().compile(is);


	}

}

package com.guygrigsby.jvarm.core.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.guygrigsby.jvarm.core.ArmProgram;
import com.guygrigsby.jvarm.core.ArmSourceCompiler;
import com.guygrigsby.jvarm.core.Memory;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

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
		Map<String, Integer> registers = new HashMap<String, Integer>();
		registers.put("R0", 1);
		registers.put("R1", 2);
		Memory memory = new Memory();
		program.run(registers, memory);
		//dump registers
		logger.trace("Registers:");
		for (String key : registers.keySet()) {
			logger.trace("{} : {}", key, registers.get(key));
		}

	}

}

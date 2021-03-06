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
import com.guygrigsby.jvarm.core.CompilerInfoCollector;
import com.guygrigsby.jvarm.core.Memory;
import com.guygrigsby.jvarm.core.Registers;
import com.guygrigsby.jvarm.core.parse.ArmSourceTokenizer;

public class ArmTest {
	
	private static final Logger logger = LogManager.getLogger(ArmTest.class);

	
	ArmSourceTokenizer tokenizer;

	@Before
	public void setUp() throws Exception {
		 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		
		InputStream is = ArmTest.class.getResourceAsStream("add.s");
		ArmProgram program = new ArmSourceCompiler().compile(is, new CompilerInfoCollector());
		Registers registers = new Registers();
		Memory memory = new Memory();
		program.run(registers, memory);
		//dump registers
		logger.trace("Registers:");
		for (String key : registers.keySet()) {
			logger.trace("{} : {}", key, registers.get(key));
		}

	}

}

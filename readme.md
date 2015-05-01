#jvARM Core

##Build

1. Run the build file in the base directory with Apache Ant 'build.xml'. This will run the tests as well.
2. jvARM was developed in Eclipse so it can be built in Eclipse as well.

##Use
The following example reads a file entitled 'add.s' of ARM source, runs the source and prints the registers contents.
```java
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
```

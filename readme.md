#jvARM Core
The following is the original abstract for this project:
> jvARM
> Java Virtual ARM Machine
> An editor, interpreter and debugger for learning ARM assembly
>
>
> This thesis takes the form of a program. It is intended to be used by students for the purpose of learning ARM assembly. ARM assembly is a reduced instruction set assembly language that is taught at Metro State University as an introduction to assembly language programming. When I was learning ARM, I found that there was a lack of adequate cross platform tools for a beginner to write and debug ARM assembly code. This added a degree of difficulty and frustration that took away from the learning experience. My hope is to provide a simple and intuitive program to facilitate learning. 
> The software will be written iteratively and incrementally. Because the software could include many options, like those of any full IDE, new functionality will be added each iteration and the end functionality is dependent on the progress at each stage. The minimum functionality will consist of parsing code and debug stepping in a standalone program with a graphical user interface. The goal of this software is to provide students with an integrated editor, interpreter and debugger that share a simple interface and can be run on Windows, OSX, and Linux. The project will also be open source and will be available to the community after this semester.
>        The editor portion of the project will provide real time grammar parsing to point out syntax errors. Ideally, it will also provide syntax highlighting.
>        The interpreter will be the major part of the project. It will parse and “run” the ARM assembly code in a Java environment. This will not be an emulator in the true sense of the word. The ARM assembly will not be compiled into object code. Rather it will be read and interpreted by a custom Java engine designed to simplify the environment for beginners to grasp the most important parts of the process. The Java environment will replicate the hardware in an ARM system, including the processor itself (ALU and registers), main memory, disk, and I/O. The design and implementation details are chosen to facilitate ease of use and ease of learning, not performance.
>        The program will be completely written in Java and therefore will be able to run on any machine with a Java 8 SE runtime environment. This could include Microsoft Windows, Apple OSX, and various distributions of Linux.
>        Finally, the project will be open source. My hope is that after this semester is over, it can be maintained by the community itself. Because it is intended to be used in academia and free for all, it will be licensed using a strong copy-left license, the GNU General Public License. 

##Build

1. Run the build file in the base directory with Apache Ant 'build.xml'. This will run the tests as well.
2. jvARM was developed in Eclipse so it can be built in Eclipse as well.

##Use Library
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
##End user
End user documentation can be found in the repo for [jvARM-fxgui](https://github.com/guygrigsby/jvARM-fxgui)
A complete binary build including GUI can be downloaded from [jvARM-fxgui releases](https://github.com/guygrigsby/jvARM-fxgui/releases)
##License
This project is licensed under the [GNU General Public License](https://www.gnu.org/copyleft/gpl.html)

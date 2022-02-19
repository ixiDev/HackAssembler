# HackAssembler for Nand2Tetris Hack Computer using Kotlin 🤩

A hack Assembler for hack computer architecture from the Nand2Tetris coursera course

- Course link [Nand2Tetris](https://www.coursera.org/learn/build-a-computer)
-

#### Description

- [AssemblerUtils](src/main/kotlin/com/ixidev/hackassembler/AssemblerUtils.kt) : Contain the LookUp table and predefined
  memory addresses
- [Parser.kt](src/main/kotlin/com/ixidev/hackassembler/Parser.kt) : The parser class, parse .asm file to list of
  instructions
- [Code.kt](src/main/kotlin/com/ixidev/hackassembler/Code.kt) : Binary code generator, translate asm instruction to
  binary

#### How to use

Clone this repo and open it in Intellij IDEA or run it from command line

```shell
  ./gradlew build run
```
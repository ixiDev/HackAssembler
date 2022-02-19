package com.ixidev.hackassembler

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertIs

/**
 * Author : Abdelmajid ID ALI
 * On : 18/02/2022
 * Email :  abdelmajid.idali@gmail.com
 */
internal class ParserTest {


    @Test
    fun test_parse_instructions_Add() {
        val parser = Parser(
            File("/home/ixi/IdeaProjects/nand2tetris/HackAssembler/src/test/resources/Add.asm"),
            SymbolTable()
        )
        val instructions = parser.parse()

        assertEquals(instructions.size, 6)
    }

    @Test
    fun test_parse_instructions_Max() {
        val parser = Parser(
            File("/home/ixi/IdeaProjects/nand2tetris/HackAssembler/src/test/resources/Max.asm"),
            SymbolTable()
        )
        val instructions = parser.parse()

        assertEquals(instructions.size, 16)
    }

    @Test
    fun test_parse_line() {
        val parser = Parser(File(""), SymbolTable())

        val instructionA = parser.parseLineInstruction("@A//")
        assertIs<HackInstruction.AInstruction>(instructionA)

        val instructionC = parser.parseLineInstruction("D=D-1;JEQ//")
        assertIs<HackInstruction.CInstruction>(instructionC)
    }

}
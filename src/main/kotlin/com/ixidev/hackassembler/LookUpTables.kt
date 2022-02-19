package com.ixidev.hackassembler

/**
 ** Author : Abdelmajid ID ALI
 ** On : 18/02/2022
 ** Email :  abdelmajid.idali@gmail.com
 **/

// C instruction :  dest = comp;jump
val DEST_LOOK_UP_TABLE = mapOf(
    "null" to "000",
    "M" to "001",
    "D" to "010",
    "MD" to "011",
    "A" to "100",
    "AM" to "101",
    "AD" to "110",
    "AMD" to "111",
)
val COMP_LOOK_UP_TABLE = mapOf(
    "0" to "0101010",
    "1" to "0111111",
    "-1" to "0111010",
    "D" to "0001100",
    "A" to "0110000",
    "!D" to "0001101",
    "!A" to "0110001",
    "-D" to "0001111",
    "-A" to "0110011",
    "D+1" to "0011111",
    "A+1" to "0110111",
    "D-1" to "0001110",
    "A-1" to "0110010",
    "D+A" to "0000010",
    "D-A" to "0010011",
    "A-D" to "0000111",
    "D&A" to "0000000",
    "D|A" to "0010101",

    "M" to "1110000",
    "!M" to "1110001",
    "-M" to "1110011",
    "M+1" to "1110111",
    "M-1" to "1110010",
    "D+M" to "1000010",
    "D-M" to "1010011",
    "M-D" to "1000111",
    "D&M" to "1000000",
    "D|M" to "1010101",
)
val JUMP_LOOK_UP_TABLE = mapOf(
    "null" to "000",
    "JGT" to "001",
    "JEQ" to "010",
    "JGE" to "011",
    "JLT" to "100",
    "JNE" to "101",
    "JLE" to "110",
    "JMP" to "111",
)

val A_LOOK_TABLE = mapOf(
    "R0" to 0,
    "R1" to 1,
    "R2" to 2,
    "R3" to 3,
    "R4" to 4,
    "R5" to 5,
    "R6" to 6,
    "R7" to 7,
    "R8" to 8,
    "R9" to 9,
    "R10" to 10,
    "R11" to 11,
    "R12" to 12,
    "R13" to 13,
    "R14" to 14,
    "R15" to 15,
    "SCREEN" to 16384,
    "KBD" to 24576,
    "SP" to 0,
    "LCL" to 1,
    "ARG" to 2,
    "THIS" to 3,
    "THAT" to 4,
)
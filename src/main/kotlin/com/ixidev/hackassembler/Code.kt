package com.ixidev.hackassembler

import java.io.File

/**
 ** Author : Abdelmajid ID ALI
 ** On : 18/02/2022
 ** Email :  abdelmajid.idali@gmail.com
 **/
class Code(
    private val program: File
) {
    private val symbolTable = SymbolTable()
    private val parser = Parser(program, symbolTable)
    private lateinit var outputFile: File

    init {
        initOutputFile()
    }

    private fun initOutputFile() {
        outputFile = File(program.parent, "${program.nameWithoutExtension}.hack")
        if (outputFile.exists()) {
            outputFile.delete()
        }
        outputFile.createNewFile()
    }


    fun assembly(): String {
        val binaryBuilder = StringBuilder()
        parser.parse().forEachIndexed { index, instruction ->
            when (instruction) {
                is HackInstruction.AInstruction -> {
                    decodeAInstruction(binaryBuilder, instruction)
                }
                is HackInstruction.CInstruction -> {
                    decodeCInstruction(binaryBuilder, instruction)
                }
                is HackInstruction.LabelInstruction -> {
                    // skip it
//                    decodeLabelInstruction(binaryBuilder, instruction)
                }
            }

            val str = binaryBuilder.toString()
            if (str.isNotBlank()) {
                if (index == 0)
                    outputFile.appendText(str)
                else
                    outputFile.appendText("\n$str")
            }
            binaryBuilder.clear()
        }
        return outputFile.absolutePath
    }

    private fun decodeCInstruction(
        binaryBuilder: StringBuilder,
        instruction: HackInstruction.CInstruction
    ) {

        binaryBuilder.append("111") // 16th bit is 1 in C instruction and 15,14th are always 11 not used
        val comp = instruction.comp.value
        val dest = instruction.dest.value
        val jump = instruction.jump.value

        if (comp.isNotNullOrEmpty()) {
            val compBinary = decodeComp(comp, instruction)
            binaryBuilder.append(compBinary)
        }
        if (dest.isNotNullOrEmpty()) {
            val destBinary = decodeDest(dest, instruction)
            binaryBuilder.append(destBinary)
        }
        if (jump.isNotNullOrEmpty()) {
            val jumpBinary = decodeJump(jump, instruction)
            binaryBuilder.append(jumpBinary)
        }

    }

    private fun decodeComp(comp: String?, instruction: HackInstruction.CInstruction): String {
        return COMP_LOOK_UP_TABLE
            .getOrDefault(comp, null)
            ?: error("Unknown Jump '$comp' at line '${instruction.lineNumber}'")
    }

    private fun decodeJump(jump: String?, instruction: HackInstruction.CInstruction): String {
        return JUMP_LOOK_UP_TABLE
            .getOrDefault(jump, null)
            ?: error("Unknown Jump '$jump' at line '${instruction.lineNumber}'")
    }

    private fun decodeDest(
        dest: String?,
        instruction: HackInstruction.CInstruction
    ): String {
        return DEST_LOOK_UP_TABLE
            .getOrDefault(dest, null)
            ?: error("Unknown destination '$dest' at line '${instruction.lineNumber}'")
    }

    private fun decodeAInstruction(
        binaryCode: StringBuilder,
        instruction: HackInstruction.AInstruction
    ) {
        binaryCode.append("0") // append 0 for A instruction
        val address = instruction.value.toIntOrNull() ?: symbolTable.getOrAdd(instruction.value)
        binaryCode.append(address.to15Binary())
    }


    private fun Int.to15Binary(): String {
        val b = Integer.toBinaryString(this)
        if (b.length < 15) {
            val builder = StringBuilder()
            repeat(15 - b.length) {
                builder.append("0")
            }
            return builder.append(b).toString()
        }
        return b
    }
}

private fun String?.isNotNullOrEmpty(): Boolean {
    return !isNullOrEmpty()
}



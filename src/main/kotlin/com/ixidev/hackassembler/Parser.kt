package com.ixidev.hackassembler

import java.io.File
import kotlin.jvm.Throws

/**
 ** Author : Abdelmajid ID ALI
 ** On : 18/02/2022
 ** Email :  abdelmajid.idali@gmail.com
 **/
class Parser(
    private val program: File,
    private val symbolTable: SymbolTable
) {


    @Throws(IllegalStateException::class)
    private fun checkFileIsValid(file: File) {
        val errorPrefix = "File '${file.name}' "
        if (!file.exists()) {
            error(errorPrefix + "Not exist")
        } else if (!file.canRead()) {
            error(errorPrefix + "is not readable !")
        }
    }

    /**
     * Parse list of hack assembly instructions
     */
    @Throws(IllegalStateException::class)
    fun parse(): List<HackInstruction> {

        checkFileIsValid(program)

        val instructions = arrayListOf<HackInstruction>()
        var lineNumber = 1
        program.forEachLine { line ->
            try {
                val trimed = line.trimStart()
                if (trimed.isBlank() or trimed.startsWith("//")) {
                    // skip
                } else {
                    val instruction: HackInstruction = parseLineInstruction(trimed)
                    // do not add labels to instruction list
                    // just add its position(address) to symbol table
                    if (instruction is HackInstruction.LabelInstruction) {
                        symbolTable.add(
                            instruction.label,
                            instructions.size
                        )
                    } else {
                        // keep tracking the instruction line number
                        instruction.lineNumber = lineNumber
                        instructions.add(instruction)
                    }
                }
                lineNumber++

            } catch (e: Exception) {
                error("Error: ${e.message} at line $lineNumber")
            }
        }

        return instructions
    }

    private fun removeCommentsFromLine(line: String): String {
        return line.substring(0, line.indexOf("//"))
    }

    fun parseLineInstruction(line: String): HackInstruction {
        val noComments: String = if (line.contains("//"))
            removeCommentsFromLine(line)
        else line
        return if (noComments.startsWith("@"))
            parseAInstruction(noComments)
        else if (noComments.isLabel()) //
            parseLabel(noComments)
        else parseCIInstruction(noComments)
    }

    private fun parseLabel(line: String): HackInstruction.LabelInstruction {
        val label = line.removePrefix("(").removeSuffix(")")
        if (label.contains(" "))
            error("Invalid whitespaces in label name '$label' ")
        return HackInstruction.LabelInstruction(label)
    }

    private fun parseCIInstruction(line: String): HackInstruction.CInstruction {
        val split = line.trim().split("[=;]".toRegex())
        if (split.isEmpty())
            error("C instruction is empty")
        return when {
            (split.size == 3) -> HackInstruction.CInstruction(
                dest = CToken.DEST(split[0]),
                comp = CToken.COMP(split[1]),
                jump = CToken.JUMP(split[2]),
            )
            (split.size == 2) and line.contains(";") -> {
                HackInstruction.CInstruction(
                    dest = CToken.DEST("null"),
                    comp = CToken.COMP(split[0]),
                    jump = CToken.JUMP(split[1]),
                )
            }
            (split.size == 2) and line.contains("=") -> {
                HackInstruction.CInstruction(
                    dest = CToken.DEST(split[0]),
                    comp = CToken.COMP(split[1]),
                    jump = CToken.JUMP("null"),
                )
            }
            else -> {
                error("Cannot parse C instruction '$line'")
            }
        }
    }

    private fun parseAInstruction(line: String): HackInstruction.AInstruction {
        // look to Symbols table
        val value = line.removePrefix("@").trimEnd() // remove only the spaces at the end
        if (value.startsWith(" ")) // @[space]xxx not valid
            error("Invalid whitespaces in A instruction ")
        return HackInstruction.AInstruction(value)
    }

}

private fun String.isLabel(): Boolean {
    val tmp = this.trimStart().trimEnd()
    return tmp.startsWith("(") and tmp.endsWith(")")
}

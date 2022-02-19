package com.ixidev.hackassembler

/**
 ** Author : Abdelmajid ID ALI
 ** On : 18/02/2022
 ** Email :  abdelmajid.idali@gmail.com
 **/

sealed class HackInstruction(var lineNumber: Int) {
    class AInstruction(val value: String, lineNumber: Int = 0) : HackInstruction(lineNumber)
    class CInstruction(
        val dest: CToken,
        val comp: CToken,
        val jump: CToken,
        lineNumber: Int = 0
    ) : HackInstruction(lineNumber)

    class LabelInstruction(val label: String, lineNumber: Int = 0) : HackInstruction(lineNumber)
}

sealed class CToken(val value: String? = null) {
    class DEST(value: String?) : CToken(value)
    class COMP(value: String?) : CToken(value)
    class JUMP(value: String?) : CToken(value)
}
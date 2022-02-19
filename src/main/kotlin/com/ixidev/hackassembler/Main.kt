package com.ixidev.hackassembler

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage :  HackAssembler path/file.asm")
        exitProcess(1)
    }
    val program = File(args.first())
    val assembler = Code(program)
    try {
        val hackPath = assembler.assembly()
        println("Assembly successfully, hack file saved to '$hackPath'")
    } catch (e: Exception) {
        println("${e.message}")
    }
}
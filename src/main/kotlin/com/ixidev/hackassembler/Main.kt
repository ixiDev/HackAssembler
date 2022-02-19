package com.ixidev.hackassembler

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage :  HackAssembler path/file.asm")
        exitProcess(1)
    }
}
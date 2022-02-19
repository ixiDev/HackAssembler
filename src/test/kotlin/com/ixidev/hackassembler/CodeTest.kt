package com.ixidev.hackassembler

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertContentEquals

/**
 * Author : Abdelmajid ID ALI
 * On : 18/02/2022
 * Email :  abdelmajid.idali@gmail.com
 */
internal class CodeTest {


    @Test
    fun test_assembly_add() {
        val url = javaClass.classLoader.getResource("Add.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        println("file saved on : " + hackFile.absoluteFile)
        val addComp = File(javaClass.classLoader.getResource("AddComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }

    @Test
    fun test_assembly_max() {
        val url = javaClass.classLoader.getResource("Max.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        val addComp = File(javaClass.classLoader.getResource("MaxComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }

    @Test
    fun test_assembly_maxL() {
        val url = javaClass.classLoader.getResource("Max.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        val addComp = File(javaClass.classLoader.getResource("MaxLComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }

    @Test
    fun test_assembly_Pong() {
        val url = javaClass.classLoader.getResource("Pong.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        val addComp = File(javaClass.classLoader.getResource("PongComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }
    @Test
    fun test_assembly_PongL() {
        val url = javaClass.classLoader.getResource("PongL.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        val addComp = File(javaClass.classLoader.getResource("PongLComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }
    @Test
    fun test_assembly_Rect() {
        val url = javaClass.classLoader.getResource("Rect.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        val addComp = File(javaClass.classLoader.getResource("RectComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }

    @Test
    fun test_assembly_RectL() {
        val url = javaClass.classLoader.getResource("RectL.asm")
        val program = File(url!!.toURI())
        val coder = Code(program)
        val path = coder.assembly()
        val hackFile = File(path)
        val addComp = File(javaClass.classLoader.getResource("RectLComp.hack")!!.toURI())
        assertContentEquals(addComp.readLines(), hackFile.readLines())
    }
}
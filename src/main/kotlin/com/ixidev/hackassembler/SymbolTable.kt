package com.ixidev.hackassembler

/**
 ** Author : Abdelmajid ID ALI
 ** On : 18/02/2022
 ** Email :  abdelmajid.idali@gmail.com
 *  Memory symbols table
 *  It contain the adress of variable and predefined variables R0...R15
 *  if the variable is not predefined so start from 16
 **/
class SymbolTable {

    private val symbolMap: HashMap<String, Int> = hashMapOf()
    private var lastFreeAddress = 16

    init {
        symbolMap.putAll(A_LOOK_TABLE)
    }

    fun add(symbol: String, value: Int) {
        symbolMap[symbol] = value
    }

    fun getOrAdd(value: String): Int {
        val add = symbolMap.getOrPut(value, defaultValue = { lastFreeAddress++ })

        return add
    }

    fun getOrNull(value: String): Int? {
        return symbolMap.getOrDefault(value, null)
    }
}
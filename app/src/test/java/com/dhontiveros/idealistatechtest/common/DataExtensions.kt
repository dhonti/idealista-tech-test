package com.dhontiveros.idealistatechtest.common



fun TypeFileResponse.getValueJson(): String? = JsonFileLoader.loadJsonString(this.stringVal)

inline fun <reified T>TypeFileResponse.getEntityJson(): T? = JsonFileLoader.loadEntity<T>(this.stringVal)

fun String.countMatches(pattern: String): Int {
    return this.split(pattern)
        .dropLastWhile { it.isEmpty() }
        .toTypedArray().size - 1
}
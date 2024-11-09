package com.dhontiveros.idealistatechtest.core.common

interface Mapper<Input,Output> {

    fun from(i: Input?): Output
    fun to(o: Output?): Input

    fun fromList(list: List<Input>?): List<Output> = list?.mapNotNull { from(it) } ?: listOf()
    fun toList(list: List<Output>?): List<Input> = list?.mapNotNull { to(it) } ?: listOf()

}
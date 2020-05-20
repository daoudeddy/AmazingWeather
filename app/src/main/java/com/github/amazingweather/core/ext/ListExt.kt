package com.github.amazingweather.core.ext

fun <E> List<E>.mostOccurringItem(): E? {
    return map { it }.groupBy { it }.mapValues { it.value.size }.maxBy { it.value }?.key
}

fun List<Double>.average()  = (sum() / size)
fun List<Int>.average()  = sum() / size
fun List<Long>.average()  = sum() / size
package com.example.eyedroptracker.utils

import java.util.concurrent.atomic.AtomicInteger

// TODO: - Need to cite this https://www.youtube.com/watch?v=D0VpASTpgmw&ab_channel=FoodieDev
object RandomIntUtil {
    private val seed = AtomicInteger()

    fun getRandomInt() = seed.getAndIncrement() + System.currentTimeMillis().toInt()
}
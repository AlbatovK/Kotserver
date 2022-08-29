package com.albatros.kotserver

import java.util.UUID
import kotlin.math.abs

data class Session(
    val id: Long = abs(UUID.randomUUID().mostSignificantBits % modValue),
    var hasStarted: Boolean = false,
    val eventPool: MutableList<Event> = mutableListOf(),
) {

    companion object {
        const val modValue = 1_000_000
    }

    val lastEventId: Long
        get() = eventPool.size - 1L

    fun obtainCurrentEvent() = eventPool.last()

    fun postEvent(event: Event) = eventPool.add(event)

    init {
        val initEvent = Event.InitEvent(0)
        eventPool.add(initEvent)
    }
}

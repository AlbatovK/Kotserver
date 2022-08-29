package com.albatros.kotserver.domain

@Suppress("unused")
sealed class Event(val posId: Long, val eventCode: Long) {
    class InitEvent(posId: Long) : Event(posId, 0L)
    class StartingEvent(posId: Long) : Event(posId, 1L)
    class MeteorSpawnedEvent(posId: Long) : Event(posId, 2L)
    class MeteorDestroyedEvent(posId: Long) : Event(posId, 3L)
    class GameOverEvent(posId: Long) : Event(posId, 4L)
}
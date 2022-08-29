package com.albatros.kotserver.controller

import com.albatros.kotserver.domain.Event
import com.albatros.kotserver.domain.Session
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/session")
@Suppress("Unused")
class SessionController {

    private val sessions: MutableList<Session> = mutableListOf()

    @GetMapping("/get")
    fun getSessions() = sessions

    @GetMapping("/create")
    fun createSession(): Long {
        return Session().let {
            sessions.add(it)
            it.id
        }
    }

    @GetMapping("/enter")
    fun enterSession(@RequestParam("session_id") sessionId: Long): ResponseEntity<Any> {
        return sessions.find { it.id == sessionId }?.let {
            if (it.hasStarted) null else {
                with(it) {
                    hasStarted = true
                    val startingEvent = Event.StartingEvent(lastEventId + 1)
                    postEvent(startingEvent)
                    ResponseEntity.ok(id)
                }
            }
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/event/last")
    fun getLastEvent(@RequestParam("session_id") sessionId: Long) =
        sessions.find { it.id == sessionId }?.obtainCurrentEvent()?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/event/post")
    fun postEvent(
        @RequestParam("session_id") sessionId: Long,
        @RequestParam("event_code") eventCode: Long
    ): ResponseEntity<Any> {

        val session = sessions.find { it.id == sessionId } ?: return ResponseEntity.notFound().build()
        val newId = session.lastEventId + 1

        val event = when (eventCode) {
            2L -> Event.MeteorSpawnedEvent(newId)
            3L -> Event.MeteorDestroyedEvent(newId)
            4L -> Event.GameOverEvent(newId)
            else -> null
        }

        event?.let(session::postEvent) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().build()
    }
}
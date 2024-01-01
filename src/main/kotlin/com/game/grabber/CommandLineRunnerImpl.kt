package com.game.grabber

import com.game.grabber.gomafia.GomafiaRestClient
import com.game.grabber.gomafia.model.GomafiaRegion
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CommandLineRunnerImpl(
        val gomafiaRestClient: GomafiaRestClient
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        /*val users = gomafiaRestClient.getUsers(2023, GomafiaRegion.FAR_EAST)
        println(users)*/
        val t = gomafiaRestClient.getTournament(908)
        println(t)
    }
}
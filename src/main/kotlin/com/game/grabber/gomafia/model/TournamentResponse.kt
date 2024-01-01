package com.game.grabber.gomafia.model

data class TournamentResponse(
        val tournamentDto: TournamentDto,
        val games: List<GameDto>,
        val tournamentResults: List<TournamentResultDto>
)
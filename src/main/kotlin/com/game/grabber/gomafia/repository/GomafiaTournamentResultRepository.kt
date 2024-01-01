package com.game.grabber.gomafia.repository;

import com.game.grabber.gomafia.entity.GomafiaTournamentResult
import org.springframework.data.jpa.repository.JpaRepository

interface GomafiaTournamentResultRepository : JpaRepository<GomafiaTournamentResult, Long> {
}
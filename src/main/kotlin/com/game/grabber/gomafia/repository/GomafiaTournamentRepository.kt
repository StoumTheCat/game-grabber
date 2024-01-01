package com.game.grabber.gomafia.repository;

import com.game.grabber.gomafia.entity.GomafiaTournament
import org.springframework.data.jpa.repository.JpaRepository

interface GomafiaTournamentRepository : JpaRepository<GomafiaTournament, Int> {
}
package com.game.grabber.gomafia.repository;

import com.game.grabber.gomafia.entity.GomafiaGame
import org.springframework.data.jpa.repository.JpaRepository

interface GomafiaGameRepository : JpaRepository<GomafiaGame, Long> {
}
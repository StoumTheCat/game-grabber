package com.game.grabber.gomafia.repository;

import com.game.grabber.gomafia.entity.GomafiaGamePlayer
import org.springframework.data.jpa.repository.JpaRepository

interface GomafiaGamePlayerRepository : JpaRepository<GomafiaGamePlayer, Long> {
}
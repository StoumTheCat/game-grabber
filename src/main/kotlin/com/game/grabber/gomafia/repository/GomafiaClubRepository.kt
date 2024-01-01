package com.game.grabber.gomafia.repository;

import com.game.grabber.gomafia.entity.GomafiaClub
import org.springframework.data.jpa.repository.JpaRepository

interface GomafiaClubRepository : JpaRepository<GomafiaClub, Int> {
}
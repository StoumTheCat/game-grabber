package com.game.grabber.gomafia.repository;

import com.game.grabber.gomafia.entity.GomafiaUser
import org.springframework.data.jpa.repository.JpaRepository

interface GomafiaUserRepository : JpaRepository<GomafiaUser, Int> {
    fun getByLogin(login: String): GomafiaUser
}
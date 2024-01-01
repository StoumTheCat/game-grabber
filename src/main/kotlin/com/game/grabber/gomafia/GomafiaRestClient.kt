package com.game.grabber.gomafia

import com.fasterxml.jackson.databind.ObjectMapper
import com.game.grabber.gomafia.model.*
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class GomafiaRestClient {
    val mapper = ObjectMapper()

    fun getClubResidents(clubId: Int): List<UserDto> {
        val resp = RestClient.builder().baseUrl("https://gomafia.pro/api").build()
                .post()
                .uri("/club/getResidents")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body("id=$clubId&limit=1000&offset=0")
                .retrieve()
                .body(String::class.java)

        return getDataList(resp!!).map { user -> mapper.convertValue(user, UserDto::class.java)}
    }

    fun getUsers(year: Int, region: GomafiaRegion): List<UserDto> {
        val resp = RestClient.builder().baseUrl("https://gomafia.pro/api").build()
                .post()
                .uri("/user/getTop")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body("year=$year&region=${region.region}&limit=10000&offset=0")
                .retrieve()
                .body(String::class.java)

        return getDataList(resp!!).map { user -> mapper.convertValue(user, UserDto::class.java)}
    }

    fun getClubs(): List<ClubDto> {
        val resp = RestClient.builder().baseUrl("https://gomafia.pro/api").build()
                .post()
                .uri("/club/getTop")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body("limit=10000&offset=0")
                .retrieve()
                .body(String::class.java)

        return getDataList(resp!!).map { club -> mapper.convertValue(club, ClubDto::class.java)}
    }

    fun getTournaments(): List<TournamentDto> {
        val resp = RestClient.builder().baseUrl("https://gomafia.pro/api").build()
                .post()
                .uri("/tournament/getAll")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body("time=finished&fsm=yes&type=all&limit=10000&offset=0")
                .retrieve()
                .body(String::class.java)

        val data = getDataList(resp!!)

        return data.map { mapper.convertValue(it, TournamentDto::class.java) }
    }

    fun getTournament(id: Int): TournamentResponse {
        val resp = RestClient.builder().baseUrl("https://gomafia.pro/api").build()
                .post()
                .uri("/tournament/get")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body("id=$id")
                .retrieve()
                .body(String::class.java)

        val data = getDataMap(resp!!)

        val games = (data["games"] as ArrayList<*>).flatMap { g  ->
            val gMap = (g as Map<*, *>)
            val gNum = gMap["gameNum"] as Int
            (gMap["game"] as ArrayList<*>).map { gdto ->
                val gg = mapper.convertValue(gdto, GameDto::class.java)
                gg.gameNum = gNum
                return@map gg
            }
        }

        val results = (data["tournament_result"] as ArrayList<*>).map { r -> mapper.convertValue(r, TournamentResultDto::class.java) }

        val tournament = mapper.convertValue(data["tournament"]!!, TournamentDto::class.java)

        return TournamentResponse(tournament, games, results)
    }

    private fun getDataMap(response: String): Map<*, *> {
        val mapped = mapper.readValue(response, Map::class.java)
        return mapped["data"] as Map<*, *>
    }

    private fun getDataList(response: String): List<*> {
        val mapped = mapper.readValue(response, Map::class.java)
        return mapped["data"] as List<*>
    }
}
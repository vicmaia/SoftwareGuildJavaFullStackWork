/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dto;

import com.mycompany.baseball.dao.PlayerDao;
import com.mycompany.baseball.dao.PlayerDaoImpl;
import com.mycompany.baseball.dao.PlayerPersistenceException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author n0252282
 */
public class Team {

    PlayerDao dao = new PlayerDaoImpl();
    private Integer teamID;
    private Integer leagueID;
    private String teamName;
//    private ArrayList<Player> players = new ArrayList<>();

    //private ArrayList<Player> playerRoster = new ArrayList<>();
    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

//    public ArrayList<Player> getPlayerRoster() {
//        return playerRoster;
//    }
//
//    public void setPlayerRoster(ArrayList<Player> playerRoster) {
//        this.playerRoster = playerRoster;
//    }
    public Integer getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(Integer leagueID) {
        this.leagueID = leagueID;
    }

//    public ArrayList<Player> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(ArrayList<Player> players) throws PlayerPersistenceException {
//        for (Player currentPlayer : dao.getAllPlayers()) {
//            if (Objects.equals(currentPlayer.getTeamID(), this.teamID)) {
//                this.players.add(currentPlayer);
//            }
//        }
//    }
}

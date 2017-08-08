/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dto;

/**
 *
 * @author n0252282
 */
public class Player {

    private Integer playerID;
    private String name;
    private String position;
    private String teamNameShort;
    private String bats;
    private String hits;
    
    public Player(Integer playerID) {
        this.playerID = playerID;
    }

    public Player(Integer playerID, String name, String position, String teamNameShort, String teamNameLong, String bats, String hits) {
        this.playerID = playerID;
        this.name = name;
        this.position = position;
        this.teamNameShort = teamNameShort;
        this.bats = bats;
        this.hits = hits;
    }
    

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamNameShort() {
        return teamNameShort;
    }

    public void setTeamNameShort(String teamNameShort) {
        this.teamNameShort = teamNameShort;
    }

    public void setTeamNameLong(String teamNameLong) {
        this.teamNameLong = teamNameLong;
    }

    public String getBats() {
        return bats;
    }

    public void setBats(String bats) {
        this.bats = bats;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }


}

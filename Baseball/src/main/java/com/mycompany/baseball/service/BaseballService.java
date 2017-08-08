/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.service;

import com.mycompany.baseball.dao.PlayerPersistenceException;
import com.mycompany.baseball.dao.TeamPersistenceException;
import com.mycompany.baseball.dto.Player;
import com.mycompany.baseball.dto.Team;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface BaseballService {

    void createPlayer(Integer playerID, Player player) throws
            PlayerPersistenceException;

    List<Team> getAllTeams() throws
            TeamPersistenceException;

    List<Player> getFilteredPlayers() throws
            PlayerPersistenceException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dao;

import com.mycompany.baseball.dto.Player;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface PlayerDao {

    Player getPlayer(Integer playerID) throws PlayerPersistenceException;

    List<Player> getAllPlayers() throws PlayerPersistenceException;

    Player addPlayer(Integer playerID, Player player) throws PlayerPersistenceException;

    Player deletePlayer(Integer playerID) throws PlayerPersistenceException;

    Player tradePlayer(Integer playerID, String teamNameShort) throws PlayerPersistenceException;
}

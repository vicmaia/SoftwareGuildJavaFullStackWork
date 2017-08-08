/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.service;

import com.mycompany.baseball.dao.PlayerDao;
import com.mycompany.baseball.dao.PlayerDaoImpl;
import com.mycompany.baseball.dao.PlayerPersistenceException;
import com.mycompany.baseball.dao.TeamPersistenceException;
import com.mycompany.baseball.dto.Player;
import com.mycompany.baseball.dto.Team;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class BaseballServiceImpl implements BaseballService{
    PlayerDao playerDao = new PlayerDaoImpl();
    
    @Override
    public void createPlayer(Integer playerID, Player player) throws PlayerPersistenceException {
        playerDao.addPlayer(playerID, player);
    }

    @Override
    public List<Team> getAllTeams() throws TeamPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Player> getFilteredPlayers() throws PlayerPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dao;

import com.mycompany.baseball.dto.League;
import com.mycompany.baseball.dto.Team;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface LeagueDao {

    League getLeague(Integer leagueID) throws TeamPersistenceException;

    List<Team> getAllLeagues() throws TeamPersistenceException;

    League addLeague(Integer leagueID) throws TeamPersistenceException;

    League deleteLeague(Integer leagueID) throws TeamPersistenceException;
    

}

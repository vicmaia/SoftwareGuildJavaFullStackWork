/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dao;

import com.mycompany.baseball.dto.Player;
import com.mycompany.baseball.dto.Team;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface TeamDao {

    Team getTeam(Integer teamID) throws TeamPersistenceException;

    List<Team> getAllTeams() throws TeamPersistenceException;

    Team addTeam(Integer teamID) throws TeamPersistenceException;

    Team deleteTeam(Integer teamID) throws TeamPersistenceException;
    

}

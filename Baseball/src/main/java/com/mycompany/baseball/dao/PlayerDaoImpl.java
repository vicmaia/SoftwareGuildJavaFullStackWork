/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.dao;

import com.mycompany.baseball.dto.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author n0252282
 */
public class PlayerDaoImpl implements PlayerDao {

    public String playerFile = "players.txt";
    public static final String DELIMITER = "::";

    private Map<Integer, Player> players = new HashMap<>();

    @Override
    public Player getPlayer(Integer playerID) throws PlayerPersistenceException {
        return players.get(playerID);
    }

    @Override
    public List<Player> getAllPlayers() throws PlayerPersistenceException {
        loadPlayers();
        return new ArrayList<>(players.values());
    }

    @Override
    public Player addPlayer(Integer playerID, Player player) throws PlayerPersistenceException {
        Player newPlayer = players.put(playerID, player);
        writePlayers();
        return newPlayer;
    }

    @Override
    public Player deletePlayer(Integer playerID) throws PlayerPersistenceException {
        Player removedPlayer = players.remove(playerID);
        writePlayers();
        return removedPlayer;
    }

    @Override
    public Player tradePlayer(Integer playerID, Integer teamID) throws PlayerPersistenceException {
        Player tradedPlayer = players.get(playerID);
        tradedPlayer.setTeamID(teamID);
        writePlayers();
        return tradedPlayer;
    }

    private void loadPlayers() throws PlayerPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(playerFile)));
        } catch (FileNotFoundException e) {
            throw new PlayerPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Player currentPlayer = new Player(Integer.parseInt(currentTokens[0]));
            currentPlayer.setTeamID(Integer.parseInt(currentTokens[1]));

            currentPlayer.setName(currentTokens[2]);

            //put item into map
            players.put(currentPlayer.getPlayerID(), currentPlayer);
        }
        // close scanner
        scanner.close();
    }

    private void writePlayers() throws PlayerPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(playerFile));
        } catch (IOException e) {
            throw new PlayerPersistenceException(
                    "Could not save player data.", e);
        }

        List<Player> playerList = this.getAllPlayers();
        for (Player currentPlayer : playerList) {
            // write the Item object to the file
            out.println(currentPlayer.getPlayerID() + DELIMITER
                    + currentPlayer.getTeamID() + DELIMITER
                    + currentPlayer.getName());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}

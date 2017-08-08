/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseball.ui;

import com.mycompany.baseball.dto.Player;
import com.mycompany.baseball.dto.Team;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class BaseballView {

    UserIO io;

    public BaseballView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("============Main Menu===========");
        io.print("1. Create New Team");
        io.print("2. Create New Player");
        io.print("3. List All Teams");
        io.print("4. List All Players on a Team");
        io.print("5. Trade a Player");
        io.print("6. Delete a Player");
        io.print("7. Exit Program");
        io.print("================================");

        return io.readInt("Please select from the above choices: ", 1, 7);
    }

    public Player getNewPlayerInfo() {
        Integer playerId = io.readInt("Please enter Player ID");
        String playerName = io.readString("Please enter Player Name");
        Integer playerTeam = io.readInt("Please enter Team Name");
        Player currentPlayer = new Player(playerId);
        currentPlayer.setName(playerName);
        currentPlayer.setTeamID(playerTeam);
        return currentPlayer;
    }

    public void displayCreatePlayerBanner() {
        io.print("=== Create Player ===");
    }

    public void displayCreatePlayerSuccessBanner() {
        io.readString(
                "Player successfully created.  Please hit enter to continue");
    }

    public void displayCreateTeamBanner() {
        io.print("=== Create Team ===");
    }

    public void displayCreateTeamSuccessBanner() {
        io.readString(
                "Team successfully created.  Please hit enter to continue");
    }

    public void displayTeamList(List<Team> teamList) {
        teamList
                .forEach((currentTeam) -> {io.print("Team #" + currentTeam.getTeamID() + ": "
                    + currentTeam.getTeamName());});
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllPlayersOnTeamBanner() {
        io.print("=== Display All Players on Team ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display Student ===");
    }

    public String getPlayerIdChoice() {
        return io.readString("Please enter the P;ayer ID.");
    }

//    public void displayPlayersOnTeam(Integer teamID) {
//        if (student != null) {
//            io.print(student.getStudentId());
//            io.print(student.getFirstName() + " " + student.getLastName());
//            io.print(student.getCohort());
//            io.print("");
//        } else {
//            io.print("No such team.");
//        }
//        io.readString("Please hit enter to continue.");
//    }

    public void displayPlayerBanner() {
        io.print("=== Remove Player ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Player successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}

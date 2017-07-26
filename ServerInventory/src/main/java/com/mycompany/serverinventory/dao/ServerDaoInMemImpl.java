/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverinventory.dao;

import com.mycompany.serverinventory.dto.Server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author n0252282
 */
public class ServerDaoInMemImpl implements ServerDao {

    private Map<String, Server> serverMap = new HashMap<>();

    @Override
    public void addServer(Server server) {
        serverMap.put(server.getName(), server);
    }

    @Override
    public Server getServer(String name) {
        return serverMap.get(name);
    }

    @Override
    public void removeServer(String name) {
        serverMap.remove(name);
    }

    @Override
    public List<Server> getAllServers() {
        return new ArrayList<Server>(serverMap.values());
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
        return serverMap.values()
                .stream()
                .collect(Collectors.groupingBy(Server::getManufacturer));// for every server call the getmanufacturer method
                //.collect(Collectors.groupingBy(s -> s.getManufacturer()));
    }

    //Just return servers filted on manufacturer
    @Override
    public List<Server> getServersByManufacturer(String manufacturer) {
        return serverMap.values()//collection of server objecs out of maps
                .stream()
                //the below predicate will equal true and return value if manufacturer passed in equals manu. for a given server
                .filter(s -> s.getManufacturer().equalsIgnoreCase(manufacturer)) // predicate - for every server get manufacturer if this return true, it keeps the server
                .collect(Collectors.toList());//collect servers that get passed to here and pass them out
    }

    @Override
    public List<Server> getServersOlderThan(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.groupingBy(Server::getManufacturer));
    }

    @Override
    public double getAverageServerAge() {
        return serverMap.values()
                .stream()
                .mapToLong(s -> s.getServerAge())
                //.mapToLong(Server::getServerAge)
                .average()
                .getAsDouble();
    }

}

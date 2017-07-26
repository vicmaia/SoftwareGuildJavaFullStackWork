/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverinventory;

import com.mycompany.serverinventory.dao.ServerDao;
import com.mycompany.serverinventory.dao.ServerDaoInMemImpl;
import com.mycompany.serverinventory.dto.Server;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        ServerDao dao = new ServerDaoInMemImpl();

        //create servers
        Server myServer = new Server();
        myServer.setName("web01");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Dell");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer);

        Server myServer1 = new Server();
        myServer1.setName("db01");
        myServer1.setIp("192.168.1.2");
        myServer1.setManufacturer("HP");
        myServer1.setRam(16);
        myServer1.setNumProcessors(25);
        myServer1.setPurchaseDate(LocalDate.parse("2013-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer1);

        Server myServer2 = new Server();
        myServer2.setName("hr124");
        myServer2.setIp("192.168.1.13");
        myServer2.setManufacturer("IBM");
        myServer2.setRam(16);
        myServer2.setNumProcessors(12);
        myServer2.setPurchaseDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer2);

        Server myServer3 = new Server();
        myServer3.setName("eng16");
        myServer3.setIp("192.168.1.13");
        myServer3.setManufacturer("IBM");
        myServer3.setRam(16);
        myServer3.setNumProcessors(12);
        myServer.setPurchaseDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer3);

        List<Server> all = dao.getAllServers();
        for (Server currentServer : all) {
            System.out.println(currentServer.getName());
        }

        List<Server> dells = dao.getServersByManufacturer("Dell");
        for (Server currentServer : dells) {
            System.out.println("Dells " + currentServer.getName());
        }
//      Netbeans provided
//        dells.forEach((current) -> {
//            System.out.println(current.getName());
//        });

        dells.stream()
                .forEach(s -> System.out.println(s.getName()));

        Map<String, List<Server>> serverMap = dao.getAllServersGroupByManufacturer();

        Set<String> manufacturers = serverMap.keySet();

        manufacturers.stream()
                .forEach(name -> {
                    System.out.println("=================================");
                    System.out.println("Manufacturer: " + name);
                    serverMap.get(name).stream()
                            .forEach(s -> System.out.println(s.getName()));
                });

    }

}

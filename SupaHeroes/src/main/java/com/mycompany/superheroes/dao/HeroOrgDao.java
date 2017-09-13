/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.HeroOrgBridge;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface HeroOrgDao {

    //HeroOrgBridge
    public void addHeroOrg(HeroOrgBridge heroOrgBridge);

    public void deleteHeroOrg(int heroID, int orgID);

    public void updateHeroOrg(HeroOrgBridge heroOrgBridge, int oldHeroID, int oldOrgID);

    public HeroOrgBridge getHeroOrg(int heroID, int orgID);

    public List<HeroOrgBridge> getAllHeroOrgs();

    //Reports
    public List<HeroOrgBridge> getOrgMembers(int orgID);

    public List<HeroOrgBridge> getHeroMembership(int heroID);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Org;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface OrgDao {

    //Orgs
    public void addOrg(Org org);

    public void deleteOrg(int orgId);

    public void updateOrg(Org org);

    public Org getOrgById(int orgID);

    public List<Org> getAllOrgs();
}

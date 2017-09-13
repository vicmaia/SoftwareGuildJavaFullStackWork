/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.HeroOrgBridge;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author n0252282
 */
public class HeroOrgDaoImpl implements HeroOrgDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // HeroOrgBridge
    private static final String SQL_ADD_HERO_ORG
            = "insert into heroesorgbridge (HeroID, OrgID"
            + ") values (?, ?)";

    private static final String SQL_DELETE_HERO_ORG
            = "delete from heroesorgbridge where HeroID = ? and OrgID = ?";

    private static final String SQL_UPDATE_HERO_ORG
            = "update heroesorgbridge set HeroID = ?, OrgID = ? "
            + "where HeroID = ? and OrgID = ?";

    private static final String SQL_SELECT_HERO_ORG
            = "select * from heroesorgbridge where HeroID = ? and OrgID = ?";

    private static final String SQL_SELECT_ALL_HERO_ORGS
            = "select * from heroesorgbridge";

    //Reports
    private static final String SQL_SELECT_ORG_MEMBERS
            = "select * from heroesorgbridge where OrgID = ?";

    private static final String SQL_SELECT_HERO_MEMBERSHIP
            = "select * from heroesorgbridge where HeroID = ?";

    //HeroOrgBridge
    private static final class HeroOrgMapper implements RowMapper<HeroOrgBridge> {

        @Override
        public HeroOrgBridge mapRow(ResultSet rs, int i) throws SQLException {
            HeroOrgBridge hob = new HeroOrgBridge();
            hob.setHeroID(rs.getInt("HeroID"));
            hob.setOrgID(rs.getInt("OrgID"));
            return hob;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHeroOrg(HeroOrgBridge hob) {

        jdbcTemplate.update(SQL_ADD_HERO_ORG,
                hob.getHeroID(),
                hob.getOrgID());
    }

    @Override
    public void deleteHeroOrg(int heroID, int orgID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG, heroID, orgID);
    }

    @Override
    public void updateHeroOrg(HeroOrgBridge hob, int oldHeroID, int oldOrgID) {
        deleteHeroOrg(oldHeroID, oldOrgID);
        addHeroOrg(hob);
    }

    @Override
    public HeroOrgBridge getHeroOrg(int heroID, int orgID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO_ORG,
                    new HeroOrgMapper(),
                    heroID, orgID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroOrgBridge> getAllHeroOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HERO_ORGS,
                new HeroOrgMapper());
    }

    //Reports
    @Override
    public List<HeroOrgBridge> getOrgMembers(int orgID) {
        List<HeroOrgBridge> heroOrgList
                = jdbcTemplate.query(SQL_SELECT_ORG_MEMBERS,
                        new HeroOrgMapper(),
                        orgID);
        return heroOrgList;
    }

    @Override
    public List<HeroOrgBridge> getHeroMembership(int heroID) {
        List<HeroOrgBridge> heroOrgList
                = jdbcTemplate.query(SQL_SELECT_HERO_MEMBERSHIP,
                        new HeroOrgMapper(),
                        heroID);
        return heroOrgList;
    }
}

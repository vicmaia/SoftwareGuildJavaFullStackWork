/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Hero;
import com.mycompany.superheroes.models.Org;
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
public class OrgDaoImpl implements OrgDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //Org Related
    private static final String SQL_ADD_ORG
            = "insert into orgs (OrgName, OrgDescription, LocationID"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_ORG
            = "delete from orgs where OrgID = ?";

    private static final String SQL_UPDATE_ORG
            = "update orgs set OrgName = ?, OrgDescription = ?, LocationID = ? "
            + "where OrgID =  ?";

    private static final String SQL_SELECT_ORG
            = "select * from orgs where OrgID = ?";

    private static final String SQL_SELECT_ALL_ORGS
            = "select * from orgs";

    //Org Related
    private static final class OrgMapper implements RowMapper<Org> {

        @Override
        public Org mapRow(ResultSet rs, int i) throws SQLException {
            Org org = new Org();
            org.setOrgName(rs.getString("OrgName"));
            org.setOrgDescription(rs.getString("OrgDescription"));
            org.setLocationID(rs.getInt("LocationID"));
            org.setOrgID(rs.getInt("OrgID"));
            return org;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrg(Org org) {

        jdbcTemplate.update(SQL_ADD_ORG,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getLocationID());

        int OrgID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        org.setOrgID(OrgID);
    }

    @Override
    public void deleteOrg(int orgID) {
        jdbcTemplate.update(SQL_DELETE_ORG, orgID);
    }

    @Override
    public void updateOrg(Org org) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getLocationID(),
                org.getOrgID());
    }

    @Override
    public Org getOrgById(int orgID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORG,
                    new OrgMapper(),
                    orgID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Org> getAllOrgs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGS,
                new OrgMapper());
    }

}

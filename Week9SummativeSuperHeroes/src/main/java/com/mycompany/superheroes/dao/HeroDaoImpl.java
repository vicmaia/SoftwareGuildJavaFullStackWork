/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Hero;
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
public class HeroDaoImpl implements HeroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //Hero Related
    private static final String SQL_ADD_HERO
            = "insert into heroes (HeroName, HeroDescription, HeroSuperPower"
            + ") values (?, ?, ?)";

    private static final String SQL_DELETE_HERO
            = "delete from heroes where HeroID = ?";

    private static final String SQL_UPDATE_HERO
            = "update heroes set HeroName = ?, HeroDescription = ?, HeroSuperPower = ? "
            + "where HeroID =  ?";

    private static final String SQL_SELECT_HERO
            = "select * from heroes where HeroID = ?";

    private static final String SQL_SELECT_ALL_HEROES
            = "select * from heroes";

    //Heroes
    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroName(rs.getString("HeroName"));
            hero.setHeroDescription(rs.getString("HeroDescription"));
            hero.setHeroSuperPower(rs.getString("HeroSuperPower"));
            hero.setHeroID(rs.getInt("HeroID"));
            return hero;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {

        jdbcTemplate.update(SQL_ADD_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getHeroSuperPower());

        int heroID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        hero.setHeroID(heroID);
    }

    @Override
    public void deleteHero(int heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO, heroID);
    }

    @Override
    public void updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getHeroSuperPower(),
                hero.getHeroID());
    }

    @Override
    public Hero getHeroById(int heroID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HERO,
                    new HeroMapper(),
                    heroID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_HEROES,
                    new HeroMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}

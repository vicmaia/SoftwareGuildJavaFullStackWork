/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superheroes.dao;

import com.mycompany.superheroes.models.Hero;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface HeroDao {

    //Heroes
    public void addHero(Hero hero);

    public void deleteHero(int heroID);

    public void updateHero(Hero hero);

    public Hero getHeroById(int heroID);

    public List<Hero> getAllHeroes();
}

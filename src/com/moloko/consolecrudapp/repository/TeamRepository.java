package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Skill;
import com.moloko.consolecrudapp.model.Team;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface TeamRepository extends GenericRepository<Team, Integer>{
    List<Team> getAll();
    void deleteById(Integer id);
    Team getById(Integer id);
    Team save(Team team);
    Team update(Team team);
}

package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Skill;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface DeveloperRepository extends GenericRepository<Developer, Integer> {
    List<Developer> getAll();
    void deleteById(Integer id);
    Developer getById(Integer id);
    Developer save(Developer developer);
    Developer update(Developer developer);
}

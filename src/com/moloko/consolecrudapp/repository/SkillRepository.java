package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Skill;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface SkillRepository extends GenericRepository<Skill, Integer>{
    List<Skill> getAll();
    void deleteById(Integer id);
    Skill getById(Integer id);
    Skill save(Skill skill);
    Skill update(Skill skill);
}

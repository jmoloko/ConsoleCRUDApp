package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Skill;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface SkillRepository {
    public List<String> getAllSkills();
    public void createAndSaveSkill(String name);
    public void updateSkillFromId(int id, String newName);
    public void deleteSkillFromId(int id);

}

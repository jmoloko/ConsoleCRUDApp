package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Skill;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Jack Milk
 */
public class SkillRepositoryImpl implements GenericRepository<String>{

    public List<Skill> skills = new ArrayList<>();
    private final String filePath = "Skills.txt";
    private final Path path = Paths.get(filePath);
    private boolean listHasChanged = true;
    private List<String> cacheForAllSkills = null;


    public void allSkillsToConsole(){
        List<String> getSkills = getAll();
        for (String skill: getSkills){
            if (!skill.equals("")) {
                String id = skill.split(";")[0];
                String name = skill.split(";")[1];
                System.out.println(id + ": " + name);
            }
        }
    }

    @Override
    public List<String> getAll() {
        if (listHasChanged) {
            List<String> allSkills = new ArrayList<>();
            try {
                allSkills = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            cacheForAllSkills = allSkills;
            listHasChanged = false;
            return allSkills;
        } else {
            return cacheForAllSkills;
        }
    }


    public void createAndSaveSkill(String name) {
        Skill skill = new Skill(name);
        skills.add(skill);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            bw.write(skill.toString());
            bw.flush();
            bw.newLine();
            listHasChanged = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateSkillFromId(int id, String name) {
       for (Skill s: skills){
           if (s.getId() == id){
               s.setName(name);
           }
       }
       try {
           List<String> skillList = Files.lines(path).collect(Collectors.toList());
           List<String> replaced = skillList.stream().
                   map(line -> line.startsWith(Integer.toString(id)) ? line.replace(line.split(";")[1], name) : line).
                   collect(Collectors.toList());
           Files.write(path, replaced);
           listHasChanged = true;
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    @Override
    public void deleteFromId(int id) {
        skills.removeIf(skill -> skill.getId() == id);
        try {
            List<String> skillList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = skillList.stream().
                    filter(line -> !line.startsWith(Integer.toString(id))).
                    collect(Collectors.toList());
            Files.write(path, replaced);
            listHasChanged = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

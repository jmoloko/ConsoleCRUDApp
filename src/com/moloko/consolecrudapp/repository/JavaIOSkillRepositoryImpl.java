package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Skill;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Jack Milk
 */
// Singleton
public class JavaIOSkillRepositoryImpl implements SkillRepository{
    private static JavaIOSkillRepositoryImpl instance;
    private final String filePath = "src/main/resources/files/skills.txt";
    private final Path path = Paths.get(filePath);

    private JavaIOSkillRepositoryImpl(){}
    public static JavaIOSkillRepositoryImpl getSkillRepo(){
        if (instance == null){
            instance = new JavaIOSkillRepositoryImpl();
        }
        return instance;
    }


    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        List<String> allSkills = new ArrayList<>();
        try {
            allSkills = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());
            for (String skill: allSkills){
                int id = Integer.parseInt(skill.split(";")[0]);
                String name = skill.split(";")[1];
                skills.add(new Skill(id, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skills;
    }


    @Override
    public Skill getById(Integer id) {
        if (!getAllId().contains(id))
            throw new NullPointerException();

        List<String> skills = new ArrayList<>();
        Skill skillById = null;
        try {
            skills = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());

            for (String skill: skills){
                int skillId = Integer.parseInt(skill.split(";")[0]);
                String name = skill.split(";")[1];
                if (id == skillId)
                    skillById = new Skill(id, name);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return skillById;
    }


    @Override
    public Skill save(Skill skill) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            bw.write(skill.toString());
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skill;
    }


    @Override
    public Skill update(Skill skill) {
        int id = skill.getId();
        String name = skill.getName();
        if (!getAllId().contains(id))
            throw new NullPointerException();
       try {
           List<String> skillList = Files.lines(path).collect(Collectors.toList());
           List<String> replaced = skillList.stream().
                   map(line -> line.startsWith(Integer.toString(id)) ? line.replace(line.split(";")[1], name) : line).
                   collect(Collectors.toList());
           Files.write(path, replaced);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return skill;
    }


    @Override
    public void deleteById(Integer id) {
        if (!getAllId().contains(id))
            throw new NullPointerException();
        try {
            List<String> skillList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = skillList.stream().
                    filter(line -> !line.startsWith(Integer.toString(id))).
                    collect(Collectors.toList());
            Files.write(path, replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLastId(){
        int maxId;
        List<Skill> allSkills = getAll();
        if (allSkills.size() == 0){
            return 0;
        } else {
            Skill max = allSkills.stream().max(Comparator.comparing(Skill::getId)).get();
            maxId = max.getId();
        }
        return maxId;
    }

    public List<Integer> getAllId(){
        List<Integer> allId = new ArrayList<>();
        List<Skill> allSkills = getAll();
        for (Skill skill: allSkills){
            allId.add(skill.getId());
        }
        return allId;
    }
}

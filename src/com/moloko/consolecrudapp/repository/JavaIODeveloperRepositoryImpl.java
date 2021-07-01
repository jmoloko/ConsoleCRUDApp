package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Skill;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class JavaIODeveloperRepositoryImpl implements DeveloperRepository{
    private static JavaIODeveloperRepositoryImpl instance;
    private final String filePath = "src/main/resources/files/developers.txt";
    private final Path path = Paths.get(filePath);

    private JavaIODeveloperRepositoryImpl(){}
    public static JavaIODeveloperRepositoryImpl getDeveloperRepo(){
        if (instance == null){
            instance = new JavaIODeveloperRepositoryImpl();
        }
        return instance;
    }


    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        List<String> allDevelopers = new ArrayList<>();
        try {
            allDevelopers = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());
            for (String dev: allDevelopers){
                developers.add(parseDeveloperFromString(dev));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public void deleteById(Integer id) {
        if (!getAllId().contains(id))
            throw new NullPointerException();
        try {
            List<String> developerList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = developerList.stream().
                    filter(line -> !line.startsWith(Integer.toString(id))).
                    collect(Collectors.toList());
            Files.write(path, replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Integer id) {
        if (!getAllId().contains(id))
            throw new NullPointerException();

        List<String> developers = new ArrayList<>();
        Developer developerById = null;
        try {
            developers = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());

            for (String dev: developers){
                int devId = Integer.parseInt(dev.split(";")[0]);
                if (id == devId)
                    developerById = parseDeveloperFromString(dev);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developerById;
    }

    @Override
    public Developer save(Developer developer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            bw.write(developer.toString());
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        int id = developer.getId();

        if (!getAllId().contains(id))
            throw new NullPointerException();

        try {
            List<String> developerList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = developerList.stream().
                    map(line -> line.startsWith(Integer.toString(id)) ? line.replace(line, developer.toString()) : line).
                    collect(Collectors.toList());
            Files.write(path, replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developer;
    }

    public Developer parseDeveloperFromString(String string){
        List<Skill> listSkillObj = new ArrayList<>();
        List<String> listSkills = new ArrayList<>();
        String skills = string.substring(string.indexOf("[") + 1, string.indexOf("]"));
        listSkills = Arrays.asList(skills.split(","));
        for (String skill: listSkills){
            int idSkill = Integer.parseInt(skill.strip().split(";")[0]);
            String nameSkill = skill.strip().split(";")[1];
            listSkillObj.add(new Skill(idSkill, nameSkill));
        }
        int devId = Integer.parseInt(string.split(";")[0]);
        String firstName = string.split(";")[1];
        String lastName = string.split(";")[2];
        return new Developer(devId, firstName, lastName, listSkillObj);
    }

    public int getLastId(){
        int maxId;
        List<Developer> allDevelopers = getAll();
        if (allDevelopers.size() == 0){
            return 0;
        } else {
            Developer max = allDevelopers.stream().max(Comparator.comparing(Developer::getId)).get();
            maxId = max.getId();
        }
        return maxId;
    }

    public List<Integer> getAllId(){
        List<Integer> allId = new ArrayList<>();
        List<Developer> allDevelopers = getAll();
        for (Developer dev: allDevelopers){
            allId.add(dev.getId());
        }
        return allId;
    }
}

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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class DeveloperRepositoryImpl implements ExtendedRepository<String, Skill>{

    public List<Developer> developers = new ArrayList<>();
    private final String filePath = "Developers.txt";
    private final Path path = Paths.get(filePath);


    @Override
    public List<String> getAll() {
        List<String> allDevelopers = new ArrayList<>();
        try {
            allDevelopers = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDevelopers;
    }

    @Override
    public void createAndSave(String[] params, List<Skill> skills) {
        Developer developer = new Developer(params[0], params[1], skills);
        developers.add(developer);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            bw.write(developer.toString());
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFromId(String[] params, List<Skill> newSkills) {
        Developer updatedDeveloper = null;
        for (Developer d: developers){
            if (d.getId() == Integer.parseInt(params[0])){
                d.setFirstName(params[1]);
                d.setLastName(params[2]);
                d.setSkills(newSkills);
                updatedDeveloper = d;
            }
        }
        try {
            List<String> developerList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = new ArrayList<>();
            for (String line : developerList) {
                String s;
                if (line.startsWith(params[0])) {
                    assert updatedDeveloper != null;
                    s = line.replace(line, updatedDeveloper.toString());
                } else {
                    s = line;
                }
                replaced.add(s);
            }
            Files.write(path, replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromId(int id) {
        developers.removeIf(developer -> developer.getId() == id);
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
}

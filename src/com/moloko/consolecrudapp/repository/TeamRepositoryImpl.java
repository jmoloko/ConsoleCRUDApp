package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Team;
import com.moloko.consolecrudapp.model.TeamStatus;

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
public class TeamRepositoryImpl implements ExtendedRepository<String, Developer>{

    public List<Team> teams = new ArrayList<>();
    private final String filePath = "Teams.txt";
    private final Path path = Paths.get(filePath);

    @Override
    public List<String> getAll() {

        List<String> allTeams = new ArrayList<>();
        try {
            allTeams = Files.lines(path).filter(line -> !line.isBlank()).filter(line -> !line.contains("DELETED")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allTeams;
    }

    @Override
    public void createAndSave(String[] params, List<Developer> developers) {

        Team team = new Team(params[0], developers);
        teams.add(team);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            bw.write(team.toString());
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateFromId(String[] params, List<Developer> newDevelopers) {
        Team updatedTeam = null;
        for (Team t: teams){
            if (t.getId() == Integer.parseInt(params[0])){
                t.setName(params[1]);
                t.setDevelopers(newDevelopers);
                updatedTeam = t;
            }
        }

        try {
            List<String> teamList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = new ArrayList<>();
            for (String line : teamList) {
                String s;
                if (line.startsWith(params[0])) {
                    assert updatedTeam != null;
                    s = line.replace(line, updatedTeam.toString());
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
        for (Team team: teams){
            if (team.getId() == id) {
                team.setStatus(TeamStatus.DELETED);
            }
        }
        try {
            List<String> teamList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = teamList.stream().
                    map(line -> line.startsWith(Integer.toString(id)) ? line.replace("ACTIVE", "DELETED") : line).
                    collect(Collectors.toList());
            Files.write(path, replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

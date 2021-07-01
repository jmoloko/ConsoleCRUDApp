package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Team;

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
public class JavaIOTeamRepositoryImpl implements TeamRepository{
    private JavaIODeveloperRepositoryImpl developer = JavaIODeveloperRepositoryImpl.getDeveloperRepo();

    private static JavaIOTeamRepositoryImpl instance;
    private final String filePath = "src/main/resources/files/teams.txt";
    private final Path path = Paths.get(filePath);

    private JavaIOTeamRepositoryImpl(){}
    public static JavaIOTeamRepositoryImpl getTeamRepo(){
        if (instance == null){
            instance = new JavaIOTeamRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Team> getAll() {
        List<Team> teams = new ArrayList<>();
        List<String> allTeams = new ArrayList<>();
        try {
            allTeams = Files.lines(path).
                    filter(line -> !line.isBlank()).
                    filter(line -> line.contains("ACTIVE")).
                    collect(Collectors.toList());
            for (String team: allTeams){
                teams.add(parseTeamFromString(team));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;

    }

    @Override
    public void deleteById(Integer id) {
        if (!getAllId().contains(id))
            throw new NullPointerException();

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

    @Override
    public Team getById(Integer id) {
        if (!getAllId().contains(id))
            throw new NullPointerException();

        List<String> teams = new ArrayList<>();
        Team teamById = null;
        try {
            teams = Files.lines(path).filter(line -> !line.isBlank()).collect(Collectors.toList());

            for (String team: teams){
                int teamId = Integer.parseInt(team.split(";")[0]);
                if (id == teamId)
                    teamById = parseTeamFromString(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamById;
    }

    @Override
    public Team save(Team team) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            bw.write(team.toString());
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public Team update(Team team) {
        int id = team.getId();

        if (!getAllId().contains(id))
            throw new NullPointerException();

        try {
            List<String> teamList = Files.lines(path).collect(Collectors.toList());
            List<String> replaced = teamList.stream().
                    map(line -> line.startsWith(Integer.toString(id)) ? line.replace(line, team.toString()) : line).
                    collect(Collectors.toList());
            Files.write(path, replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return team;
    }

    public Team parseTeamFromString(String string){
        List<Developer> developers = new ArrayList<>();
        List<String> listDevelopers = new ArrayList<>();
        String stringDevelopers = string.substring(string.indexOf("[")+1, string.lastIndexOf("]"));
        listDevelopers = Arrays.asList(stringDevelopers.split("(?<=]), "));
        for (String dev: listDevelopers){
            developers.add(developer.parseDeveloperFromString(dev.strip()));
        }
        int teamId = Integer.parseInt(string.split(";")[0]);
        String name = string.split(";")[1];

        return new Team(teamId, name, developers);
    }

    public int getLastId(){
        int maxId;
        List<Team> allTeams = getAll();
        if (allTeams.size() == 0){
            return 0;
        } else {
            Team max = allTeams.stream().max(Comparator.comparing(Team::getId)).get();
            maxId = max.getId();
        }
        return maxId;
    }

    public List<Integer> getAllId(){
        List<Integer> allId = new ArrayList<>();
        List<Team> allTeams= getAll();
        for (Team team: allTeams){
            allId.add(team.getId());
        }
        return allId;
    }
}

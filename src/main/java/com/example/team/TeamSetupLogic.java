package com.example.team;

import com.example.model.player.Player;
import com.example.repository.playerrepository.GoalkeeperRepository;
import com.example.repository.playerrepository.OutfieldPlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
@Slf4j
public class TeamSetupLogic implements ITeamSetup {
    //10 outfield players and a goalkeeper
    //once there are more than 10 players in a team, a #first eleven starter will have to be
    @Autowired
    private OutfieldPlayerRepository outfieldPlayerRepository;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    public TeamSetupLogic(OutfieldPlayerRepository outfieldPlayerRepository, GoalkeeperRepository goalkeeperRepository) {
        this.outfieldPlayerRepository = outfieldPlayerRepository;
        this.goalkeeperRepository = goalkeeperRepository;
    }

    public List<Player> returnStartingEleven(String club){
        List<Player> outfieldPlayers = outfieldPlayerRepository.findAllPlayersClub(club);
        log.info("List of players: " + outfieldPlayers.size());
        System.out.println("List of players " + outfieldPlayers.size());
        List<Player> goalkeepers = goalkeeperRepository.findAllPlayersClub(club);
        Player goalkeeper = returnStartingKeeper(goalkeepers);
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(goalkeeper);
        allPlayers.addAll(outfieldPlayers);
        returnPositionsAsJsonArray(allPlayers);
        Map<String, Player> positions= new HashMap<>();
        allPlayers = assignPlayerToPosition(positions, club, allPlayers);
        return allPlayers;
    }

    public Player returnStartingKeeper(List<Player> goalkeepers){
        int highestOverall = 0;
        Player currentNumber1 = null;
        for(Player goalkeeper: goalkeepers){
            if(goalkeeper.getOverall() > highestOverall){
                currentNumber1 = goalkeeper;
                highestOverall = goalkeeper.getOverall();
            }
        }
        return currentNumber1;

    }

    public void returnPositionsAsJsonArray(List<Player> players){
        for(Player player: players){
            JSONArray naturalPos = new JSONArray(player.getPosition());
            player.setPositionsNatural(naturalPos);

            JSONArray accPos = new JSONArray(player.otherPositions);
            player.setPositionsAcc(accPos);
        }

    }

    public List<Player> assignPlayerToPosition(Map<String,Player> positions, String teamName, List<Player> team){
        positions.put("GK",null);
        positions.put("DL",null);
        positions.put("DCR",null);
        positions.put("DCL",null);
        positions.put("DR",null);
        positions.put("DM",null);
        positions.put("MCR",null);
        positions.put("MCL",null);
        positions.put("MR",null);
        positions.put("ML",null);
        positions.put("ST",null);

        for(Player player: team){
            List<Object> nat = player.getPositionsNatural().toList();
            List<Object> acc = player.getPositionsAcc().toList();
            List<Object> newNat = new ArrayList<>();
            List<Object> newAcc = new ArrayList<>();
            for(Object obj: nat){
                String str = obj.toString().replace("A", "");
                newNat.add(str);
            }
            for(Object obj: acc){
                String str = obj.toString().replace("A", "");
                newAcc.add(str);
            }

            JSONArray newNatJson = new JSONArray(newNat);
            JSONArray newAccJson = new JSONArray(newAcc);

            player.setPositionsNatural(newNatJson);
            player.setPositionsAcc(newAccJson);

        }

        for (String position : positions.keySet()) {
            for (Player player : team) {
                List<Object> natPositions = player.getPositionsNatural().toList();
                List<Object> accPositions = player.getPositionsAcc().toList();
                if (natPositions.contains(position)) {
                    positions.put(position, player);
                    team.remove(player);
                    break;
                }else if(natPositions.contains("DC") && position.equals("DCL") && positions.get(position) == null){
                    positions.put(position, player);
                    team.remove(player);
                    break;
                }else if(natPositions.contains("DC") &&position.equals("DCR") && positions.get(position) == null){
                    positions.put(position, player);
                    team.remove(player);
                    break;
                }else if(natPositions.contains("MC") &&position.equals("MCR") && positions.get(position) == null){
                    positions.put(position, player);
                    team.remove(player);
                    break;
                }else if(natPositions.contains("MC") &&position.equals("MCL") && positions.get(position) == null){
                    positions.put(position, player);
                    team.remove(player);
                    break;
                }
            }
        }
            for (String position : positions.keySet()){
                for(Player player: team){
                    List<Object> natPositions = player.getPositionsNatural().toList();
                    List<Object> accPositions = player.getPositionsAcc().toList();
                    String positionR = position + 'R';
                    String positionL = position + 'L';
                    if (accPositions.contains(position) && positions.get(position) == null)  {
                        positions.put(position, player);
                        team.remove(player);
                        break;
                    }else if(accPositions.contains("DC") &&position.equals("DCL") && positions.get(position) == null){
                        positions.put(position, player);
                        team.remove(player);
                        break;
                    }else if(accPositions.contains("DC") &&position.equals("DCR") && positions.get(position) == null){
                        positions.put(position, player);
                        team.remove(player);
                        break;
                    }else if(accPositions.contains("MC") &&position.equals("MCR") && positions.get(position) == null){
                        positions.put(position, player);
                        team.remove(player);
                        break;
                    }else if(accPositions.contains("MC") &&position.equals("MCL") && positions.get(position) == null){
                        positions.put(position, player);
                        team.remove(player);
                        break;
                    }
                }
//                team.remove(player);
//                break;



        }
            if(teamName.equals("Tottenham Hotspur")){
                Player player = positions.get("ST"); // hardcode needs to br removed
                positions.put("ST",team.get(team.size()-1));
                positions.put("ML",player);
                player.setStartingPosition("ML");
            }
            team = assignStartingPosition(positions);
            return team;
    }
    public void assignPlayerToPosition(Map<String,Player> positions, String teamName, String dummy){
        List<Player> team = returnStartingEleven(teamName);
        for (String position : positions.keySet()){
            //get a list of all players that can play in the position
            List<Player> canPlay = new ArrayList<>();
            for(Player player: team){
                List<Object> natPositions = player.getPositionsNatural().toList();
                List<Object> accPositions = player.getPositionsAcc().toList();
                List<Object> allPositions = Stream.concat(natPositions.stream(), accPositions.stream()).toList();
                if(allPositions.contains(position)){
                    canPlay.add(player);
                }
            }
        }

    }

    public List<Player> assignStartingPosition(Map<String,Player> positions){
        List<Player> players = new ArrayList<>();
        for(String key: positions.keySet()){
            Player player = positions.get(key);
            player.setStartingPosition(key);
            players.add(player);

        }
        return players;
    }

    public Team createTeam(String teamName) {
        Team team = new Team();
        team.setTeamName(teamName);
        List<Player> teamList = returnStartingEleven(teamName);
        List<Player> startingPlayers = new ArrayList<>();
        for (Player player : teamList) {
            if (player.getStartingPosition().equals("GK")) {
                team.setGk(player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("DL")){
                team.setDl(player);
                team.getDefenders().put("DL", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("DCL")){
                team.setDcl(player);
                team.getDefenders().put("DCL", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("DCR")){
                team.setDcr(player);
                team.getDefenders().put("DCR", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("DR")){
                team.setDr(player);
                team.getDefenders().put("DR", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("DM")){
                team.setDm(player);
                team.getMidfielders().put("DM", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("MR")){
                team.setMr(player);
                team.getAttackers().put("MR", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("MCR")){
                team.setMcr(player);
                team.getMidfielders().put("MCR", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("MCL")){
                team.setMcl(player);
                team.getMidfielders().put("MCL", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("ML")){
                team.setMl(player);
                team.getAttackers().put("ML", player);
                startingPlayers.add(player);
            }else if(player.getStartingPosition().equals("ST")){
                team.setSt(player);
                team.getAttackers().put("DL", player);
                startingPlayers.add(player);
            }
        }

        team.setPlayers(startingPlayers);
        return team;
    }
}



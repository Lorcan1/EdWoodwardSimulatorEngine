package com.example.team;

import com.example.model.JsonArray;
import com.example.model.Player;
import com.example.repository.GoalkeeperRepository;
import com.example.repository.OutfieldPlayerRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TeamSetup {
    //10 outfield players and a goalkeeper
    //once there are more than 10 players in a team, a #first eleven starter will have to be
    private OutfieldPlayerRepository outfieldPlayerRepository;
    private GoalkeeperRepository goalkeeperRepository;


    public TeamSetup(OutfieldPlayerRepository outfieldPlayerRepository, GoalkeeperRepository goalkeeperRepository) {
        this.outfieldPlayerRepository = outfieldPlayerRepository;
        this.goalkeeperRepository = goalkeeperRepository;
    }




    public List<Player> returnStartingEleven(String club){
        List<Player> outfieldPlayers = new ArrayList<>();
        List<Player> goalkeepers = new ArrayList<>();
        outfieldPlayers = outfieldPlayerRepository.findAllPlayersClub(club);
        goalkeepers = goalkeeperRepository.findAllPlayersClub(club);
        Player goalkeeper = returnStartingKeeper(goalkeepers);
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(goalkeeper);
        allPlayers.addAll(outfieldPlayers);
        returnPositionsAsJsonArray(allPlayers);
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
            player.setPositionsNaturalArray(naturalPos);

            JSONArray accPos = new JSONArray(player.otherPositions);
            player.setPositionsAccArray(accPos);
        }

    }

    public Map<String,Player> assignPlayerToPosition(Map<String,Player> positions, List<Player> team){
        for (String position : positions.keySet()) {
            for (Player player : team) {
                List<Object> natPositions = player.getPositionsNaturalArray().toList();
                List<Object> accPositions = player.getPositionsAccArray().toList();
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
                    List<Object> natPositions = player.getPositionsNaturalArray().toList();
                    List<Object> accPositions = player.getPositionsAccArray().toList();
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
        return positions;
    }
}



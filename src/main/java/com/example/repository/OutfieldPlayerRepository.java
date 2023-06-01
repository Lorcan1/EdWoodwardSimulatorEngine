package com.example.repository;

import com.example.model.player.OutfieldPlayer;
import com.example.model.player.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutfieldPlayerRepository extends CrudRepository<OutfieldPlayer,Integer> {
    @Query(value = "SELECT p FROM OutfieldPlayer p WHERE p.club = ?1")
    List<OutfieldPlayer> findOutfieldPlayersClub(String club);

    @Query(value = "SELECT p FROM OutfieldPlayer p WHERE p.club = ?1")
    List<Player> findAllPlayersClub(String club);
}

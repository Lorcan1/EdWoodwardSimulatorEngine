package com.example.repository;

import com.example.model.OutfieldPlayer;
import com.example.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutfieldPlayerRepository extends CrudRepository<OutfieldPlayer,Integer> {
    @Query(value = "SELECT p FROM OutfieldPlayer p WHERE p.club = ?1")
    List<OutfieldPlayer> findAllPlayersClub(String club);
}

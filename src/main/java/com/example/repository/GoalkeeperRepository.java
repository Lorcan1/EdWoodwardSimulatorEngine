package com.example.repository;

import com.example.model.Goalkeeper;
import com.example.model.OutfieldPlayer;
import com.example.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalkeeperRepository extends CrudRepository<Goalkeeper,Integer> {
    @Query(value = "SELECT p FROM Goalkeeper p WHERE p.club = ?1")
    List<Goalkeeper> findGoalkeeperPlayersClub(String club);

    @Query(value = "SELECT p FROM Goalkeeper p WHERE p.club = ?1")
    List<Player> findAllPlayersClub(String club);
}
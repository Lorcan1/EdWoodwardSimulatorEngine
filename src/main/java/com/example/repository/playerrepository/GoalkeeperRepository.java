package com.example.repository.playerrepository;

import com.example.model.player.Goalkeeper;
import com.example.model.player.Player;
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
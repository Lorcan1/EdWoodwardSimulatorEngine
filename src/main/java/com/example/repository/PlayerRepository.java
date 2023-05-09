package com.example.repository;

import com.example.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface PlayerRepository extends CrudRepository<Player,Integer> {

//    @Query(value = "SELECT p FROM Player p WHERE p.club = ?1")
//    List<Player> findAllPlayersClub(String club);
}

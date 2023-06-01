package com.example.repository;

import com.example.model.player.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PlayerRepository extends CrudRepository<Player,Integer> {

//    @Query(value = "SELECT p FROM Player p WHERE p.club = ?1")
//    List<Player> findAllPlayersClub(String club);
}

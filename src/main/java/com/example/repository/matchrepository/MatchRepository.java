package com.example.repository.matchrepository;


import com.example.model.matchmodel.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match,Integer> {

    @Query("SELECT m FROM Match m WHERE m.matchDate = :today")
    List<Match> findMatchesByDate(@Param("today") Date today);
}

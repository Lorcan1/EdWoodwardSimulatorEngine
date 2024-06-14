package com.example.repository.resultsrepository;

import com.example.model.results.Results;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ResultsRepository extends CrudRepository<Results,Integer> {
    @Query("SELECT r FROM Results r WHERE r.date = :today")
    List<Results> findMatchesByDate(@Param("today") Date today);
}

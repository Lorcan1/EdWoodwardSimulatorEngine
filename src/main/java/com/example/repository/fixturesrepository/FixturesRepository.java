package com.example.repository.fixturesrepository;


import com.example.model.fixtures.Fixtures;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FixturesRepository extends CrudRepository<Fixtures,Integer> {

    @Query("SELECT m FROM Fixtures m WHERE m.date = :today")
    List<Fixtures> findFixturesByDate(@Param("today") Date today);
}

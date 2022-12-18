package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {

    @Query(value = "SELECT * FROM guides", nativeQuery = true)
    Optional<List<Guide>> getAllGuides();

    @Query(value = "SELECT * FROM guides WHERE guides.game_id IN (SELECT games.id FROM games WHERE games.name=:game_name)", nativeQuery = true)
    Optional<List<Guide>> getAllGuidesByGameName(@Param("game_name") String game_name);


}

package project.poo2.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.poo2.entities.Event;

@Repository
public interface EventRepository extends JpaRepository <Event,Long>{
    @Query("SELECT e FROM Event e " +
    "WHERE" +
    "(LOWER(e.name)         LIKE  LOWER(CONCAT('%', :eventName,'%'))) AND " +
    "(LOWER(e.description)  LIKE  LOWER(CONCAT('%', :eventDescription,'%'))) AND " +
    "(LOWER(e.place)        LIKE  LOWER(CONCAT('%', :eventPlace,'%'))) AND " +
    "e.startDate > :eventStartDate"
    )
    public Page <Event> findAll(Pageable pageRequest, String eventName, String eventDescription, String eventPlace, LocalDate eventStartDate);
}
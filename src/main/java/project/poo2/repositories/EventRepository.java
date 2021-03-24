package project.poo2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.poo2.entities.Event;

@Repository
public interface EventRepository extends JpaRepository <Event,Long>{
    @Query("SELECT e FROM Event e ")
    public Page <Event> findAll(Pageable pageRequest);
}
package project.poo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.poo2.entities.Event;

@Repository
public interface EventRepository extends JpaRepository <Event,Long>{

}
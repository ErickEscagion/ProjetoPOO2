package project.poo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.poo2.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    
}

package project.poo2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.poo2.entities.Ticket;
import project.poo2.entities.TicketType;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("SELECT t FROM Ticket t " +
    "LEFT JOIN t.event e " +
    "WHERE " +
    "(e.id = :eventId) AND " +
    "(:ticketType = null OR t.type = :ticketType)"
    )
    public Page <Ticket> findAllByEventId(Pageable pageRequest, Long eventId, TicketType ticketType);
}

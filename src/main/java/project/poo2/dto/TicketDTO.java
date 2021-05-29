package project.poo2.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import project.poo2.entities.Ticket;
import project.poo2.entities.TicketType;

public class TicketDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "The ticket type is mandatory!")
    private TicketType type;
    
    private Instant date;

    @NotNull(message = "The ticket price is mandatory!")
    private double price;
    
    @ManyToOne
    @JoinColumn(name="ATTEND_USER_ID")
    private AttendDTO attend;
    
    @ManyToOne
    @JoinColumn(name="EVENT_ID")
    private EventDTO event;

    public TicketDTO() {}

    public TicketDTO(Ticket ticket, AttendDTO attend, EventDTO event) {
        this.id = ticket.getId();
        this.type = ticket.getType();
        this.date = ticket.getDate();
        this.price = ticket.getPrice();
        this.attend = attend;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AttendDTO getAttend() {
        return attend;
    }

    public void setAttend(AttendDTO attend) {
        this.attend = attend;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TicketDTO other = (TicketDTO) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

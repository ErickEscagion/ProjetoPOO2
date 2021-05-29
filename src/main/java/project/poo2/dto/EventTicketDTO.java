package project.poo2.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import project.poo2.entities.Event;
import project.poo2.entities.Ticket;
import project.poo2.entities.TicketType;

public class EventTicketDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "The ticket type is mandatory!")
    private TicketType type;
    
    private Instant date;

    @NotNull(message = "The ticket price is mandatory!")
    private double price;

    @NotNull(message = "The amount of free tickets is mandatory!")
    private int amountFreeTickets;

    @NotNull(message = "The amount of paid tickets is mandatory!")
    private int amountPaidTickets;

    @NotNull(message = "The selled amount of free tickets is mandatory!")
    private int freeTicketsSelled;

    @NotNull(message = "The selled amount of paid tickets is mandatory!")
    private int paidTicketsSelled;

    @NotNull(message = "The ticket price is mandatory!")
    private double priceTicket;
    
    @ManyToOne
    @JoinColumn(name="ATTEND_USER_ID")
    private AttendDTO attend;

    public EventTicketDTO() {}

    public EventTicketDTO(Ticket ticket, Event event) {
        this.id = ticket.getId();
        this.type = ticket.getType();
        this.date = ticket.getDate();
        this.price = ticket.getPrice();
        this.amountFreeTickets = event.getAmountFreeTickets();
        this.amountPaidTickets = event.getAmountPaidTickets();
        this.freeTicketsSelled = event.getFreeTicketsSelled();
        this.paidTicketsSelled = event.getPaidTicketsSelled();
        this.priceTicket = event.getPriceTicket();
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

    public int getAmountFreeTickets() {
        return amountFreeTickets;
    }

    public void setAmountFreeTickets(int amountFreeTickets) {
        this.amountFreeTickets = amountFreeTickets;
    }

    public int getAmountPaidTickets() {
        return amountPaidTickets;
    }

    public void setAmountPaidTickets(int amountPaidTickets) {
        this.amountPaidTickets = amountPaidTickets;
    }

    public int getFreeTicketsSelled() {
        return freeTicketsSelled;
    }

    public void setFreeTicketsSelled(int freeTicketsSelled) {
        this.freeTicketsSelled = freeTicketsSelled;
    }

    public int getPaidTicketsSelled() {
        return paidTicketsSelled;
    }

    public void setPaidTicketsSelled(int paidTicketsSelled) {
        this.paidTicketsSelled = paidTicketsSelled;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public AttendDTO getAttendDTO() {
        return attend;
    }

    public void setAttendDTO(AttendDTO attend) {
        this.attend = attend;
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
        EventTicketDTO other = (EventTicketDTO) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

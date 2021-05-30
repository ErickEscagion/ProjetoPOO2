package project.poo2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;

import project.poo2.entities.Event;

public class EventTicketDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

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

    private Page<TicketDTO> page;

    public EventTicketDTO() {}

    public EventTicketDTO(Page<TicketDTO> page, Event event) {
        this.page = page;
        this.amountFreeTickets = event.getAmountFreeTickets();
        this.amountPaidTickets = event.getAmountPaidTickets();
        this.freeTicketsSelled = event.getFreeTicketsSelled();
        this.paidTicketsSelled = event.getPaidTicketsSelled();
        this.priceTicket = event.getPriceTicket();
    }

    public Page<TicketDTO> getPage() {
        return page;
    }

    public void setPage(Page<TicketDTO> page) {
        this.page = page;
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
}

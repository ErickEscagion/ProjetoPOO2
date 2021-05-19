package project.poo2.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

import project.poo2.entities.Event;

public class EventDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;   
    
    @NotBlank(message = "The name of the event is mandatory!")
    private String name;

    @NotBlank(message = "The event description is mandatory!")
    @Length(max=200, message = "The event description must have a maximum of 200 characters")
    private String description;

    @NotBlank(message = "The event start date is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotBlank(message = "The event end date is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotBlank(message = "The event start time is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotBlank(message = "The event end time is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotBlank(message = "Email is mandatory!")
    @Email
    private String emailContact;

    @ManyToOne
    @JoinColumn(name="ADMIN_USER_ID")
    private AdminDTO admin;

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
    
    public EventDTO(){
        
    }
    
    public EventDTO(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.emailContact = event.getEmailContact();
        this.amountFreeTickets = event.getAmountFreeTickets();
        this.amountPaidTickets = event.getAmountPaidTickets();
        this.priceTicket = event.getPriceTicket();
        this.freeTicketsSelled = event.getFreeTicketsSelled();
        this.paidTicketsSelled = event.getPaidTicketsSelled();

        if (event.getAdmin() != null) {
            this.admin = new AdminDTO(event.getAdmin());
        }
    }
    
    public EventDTO(Long id, @NotBlank(message = "The name of the event is mandatory!") String name,
    @NotBlank(message = "The event description is mandatory!") @Length(max = 200, message = "The event description must have a maximum of 200 characters") String description,
    LocalDate startDate,
    LocalDate endDate,
    LocalTime startTime,
    LocalTime endTime,
    @NotBlank(message = "Email is mandatory!") @Email String emailContact) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.emailContact = emailContact;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    public String getEmailContact() {
        return emailContact;
    }
    
    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public AdminDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDTO admin) {
        this.admin = admin;
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

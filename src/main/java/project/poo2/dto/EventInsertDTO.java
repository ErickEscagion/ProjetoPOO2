package project.poo2.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

public class EventInsertDTO {
    @NotBlank(message = "The name of the event is mandatory!")
    private String name;

    @NotBlank(message = "The event description is mandatory!")
    @Length(max=200, message = "The event description must have a maximum of 200 characters")
    private String description;

    @NotNull(message = "The event start date is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @NotNull(message = "The event end date is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    @NotNull(message = "The event start time is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;
    
    @NotNull(message = "The event end time is mandatory!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotBlank(message = "Email is mandatory!")
    @Email
    private String emailContact;

    @NotNull(message = "The amount of free tickets is mandatory!")
    private Integer amountFreeTickets;

    @NotNull(message = "The amount of paid tickets is mandatory!")
    private Integer amountPaidTickets;

    @NotNull(message = "The ticket price is mandatory!")
    private Double priceTicket;

    @NotNull(message = "The admin id is mandatory!")
    private Long adminId;

    @NotNull(message = "The place id is mandatory!")
    private Long placeId;

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

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }
}

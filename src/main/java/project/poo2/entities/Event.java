package project.poo2.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

import project.poo2.dto.EventInsertDTO;

@Entity
@Table(name = "TB_EVENT")
public class Event implements Serializable{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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

    // @JsonIgnoreProperties("admin")
    @ManyToOne
    @JoinColumn(name="ADMIN_USER_ID")
    private Admin admin;

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
    
    @ManyToMany
    @JoinTable(
        name="TB_PLACE_EVENT",
        joinColumns =  @JoinColumn(name="EVENT_ID"),
        inverseJoinColumns = @JoinColumn(name="PLACE_ID")
    )
    private List<Place> places;
    
    public Event() {

    }

    public Event(EventInsertDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.emailContact = dto.getEmailContact();
        this.amountFreeTickets = dto.getAmountFreeTickets();
        this.amountPaidTickets = dto.getAmountPaidTickets();
        this.priceTicket = dto.getPriceTicket();
	}

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public List<Place> getPlaces() {
        return places;
    }

    public void addPlace(Place place) {
        this.places.add(place);
    }

    public void removePlace(Place place) {
        this.places.remove(place);
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
        Event other = (Event) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        return true;
    }
}

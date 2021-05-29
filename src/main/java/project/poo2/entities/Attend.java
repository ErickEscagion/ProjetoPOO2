package project.poo2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import project.poo2.dto.AttendDTO;

@Entity
@Table(name="TB_ATTEND")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Attend extends BaseUser {
    
    @NotNull(message = "The balance is mandatory!")
    @Min(value = 0, message = "The balance must not be negative")
    private double balance;

    @OneToMany(mappedBy = "attend")
    private List<Ticket> tickets;


    public Attend() {

    }

    public Attend(@NotNull(message = "The balance is mandatory!") @Min(value = 0, message = "The balance must not be negative") Double balance) {
        this.balance = balance;
    }

    public Attend(Long id, @NotBlank(message = "The name of the event is mandatory!") String name,
            @NotBlank(message = "Email is mandatory!") @Email String email,
            @NotNull(message = "The balance is mandatory!") @Min(value = 0, message = "The balance must not be negative") double balance) {
        super(id, name, email);
        this.balance = balance;
    }

    public Attend(AttendDTO attendDTO) {
        super(attendDTO.getName(), attendDTO.getEmail());
        this.balance = attendDTO.getBalance();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        if (ticket.getType() == TicketType.PAID)
            this.balance -= ticket.getPrice();
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        if (ticket.getType() == TicketType.PAID)
            this.balance += ticket.getPrice();
    }
}

package project.poo2.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import project.poo2.dto.AttendDTO;

@Entity
@Table(name="TB_ATTEND")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Attend extends BaseUser {
    
    @NotNull(message = "The balance is mandatory!")
    private double balance;


    @OneToMany(mappedBy = "admin")
    private List<Event> events = new ArrayList<>();


    public Attend(){

    }

    public Attend(@NotNull(message = "The balance is mandatory!") Double balance) {
        this.balance = balance;
    }

    public Attend(Long id, @NotBlank(message = "The name of the event is mandatory!") String name,
            @NotBlank(message = "Email is mandatory!") @Email String email,
            @NotNull(message = "The balance is mandatory!") double balance) {
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

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
}

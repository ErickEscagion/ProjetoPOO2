package project.poo2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import project.poo2.entities.Attend;
import project.poo2.entities.BaseUser;

public class AttendDTO extends BaseUser {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The balance is mandatory!")
    @Min(value = 0, message = "The balance must not be negative")
    private double balance;

    public AttendDTO() {

    }

    public AttendDTO(@NotNull(message = "The balance is mandatory!") @Min(value = 0, message = "The balance must not be negative") double balance) {
        this.balance = balance;
    }

    public AttendDTO(Long id, @NotBlank(message = "The name of the event is mandatory!") String name,
            @NotBlank(message = "Email is mandatory!") @Email String email,
            @NotNull(message = "The balance is mandatory!") @Min(value = 0, message = "The balance must not be negative") double balance) {
        super(id, name, email);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AttendDTO(Attend attend){
        this.setId(attend.getId());
        this.setName(attend.getName());
        this.setEmail(attend.getEmail());
        this.balance = attend.getBalance();
    }  

}

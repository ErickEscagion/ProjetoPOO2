package project.poo2.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_ADMIN")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Admin extends BaseUser {
    
    @NotBlank(message = "The phone Number is mandatory!")
    private String phoneNumber;


    @OneToMany(mappedBy = "admin")
    private List<Event> events = new ArrayList<>();


    public Admin(){

    }

    public Admin(@NotBlank(message = "The phone Number is mandatory!") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Admin(Long id, @NotBlank(message = "The name of the event is mandatory!") String name,
            @NotBlank(message = "Email is mandatory!") @Email String email,
            @NotBlank(message = "The phone Number is mandatory!") String phoneNumber) {
        super(id, name, email);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public Admin(Admin admin){
        this.phoneNumber = admin.getPhoneNumber();
    }

}

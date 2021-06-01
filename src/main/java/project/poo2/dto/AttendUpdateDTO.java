package project.poo2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import project.poo2.entities.Attend;
import project.poo2.entities.BaseUser;

public class AttendUpdateDTO extends BaseUser {

    private static final long serialVersionUID = 1L;

    public AttendUpdateDTO() {

    }

    public AttendUpdateDTO(Long id, @NotBlank(message = "The name of the event is mandatory!") String name,
            @NotBlank(message = "Email is mandatory!") @Email String email) {
        super(id, name, email);
    }

    public AttendUpdateDTO(Attend attend){
        this.setId(attend.getId());
        this.setName(attend.getName());
        this.setEmail(attend.getEmail());
    }  

}

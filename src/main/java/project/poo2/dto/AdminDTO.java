package project.poo2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import project.poo2.entities.Admin;
import project.poo2.entities.BaseUser;

public class AdminDTO extends BaseUser{

    private static final long serialVersionUID = 1L;


    @NotBlank(message = "The phone Number is mandatory!")
    @Pattern(regexp="(^$|[0-9]{11})")
    private String phoneNumber;


    public AdminDTO(){

    }

    public AdminDTO(@NotBlank(message = "The phone Number is mandatory!") @Pattern(regexp="(^$|[0-9]{11})") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AdminDTO(Long id, @NotBlank(message = "The name of the A is mandatory!") String name,
            @NotBlank(message = "Email is mandatory!") @Email String email,
            @NotBlank(message = "The phone Number is mandatory!") @Pattern(regexp="(^$|[0-9]{11})") String phoneNumber) {
        super(id, name, email);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AdminDTO(Admin admin){
        this.setId(admin.getId());
        this.setName(admin.getName());
        this.setEmail(admin.getEmail());
        this.phoneNumber = admin.getPhoneNumber();
    }  

}

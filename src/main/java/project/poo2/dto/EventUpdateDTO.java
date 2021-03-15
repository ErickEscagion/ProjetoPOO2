package project.poo2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class EventUpdateDTO {
    @NotBlank(message = "The name of the event is mandatory!")
    private String name;

    @NotBlank(message = "The event description is mandatory!")
    @Length(max=200, message = "The event description must have a maximum of 200 characters")
    private String description;

    @NotBlank(message = "Email is mandatory!")
    @Email
    private String emailContact;

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

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    
}

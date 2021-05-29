package project.poo2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import project.poo2.entities.TicketType;

public class TicketInsertDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @NotNull(message = "The ticket type is mandatory!")
    private TicketType type;

    @NotNull(message = "The attend id is mandatory!")
    private long attendId;

    public TicketInsertDTO() {}

    public long getAttendId() {
        return attendId;
    }

    public void setAttendId(long attendId) {
        this.attendId = attendId;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}

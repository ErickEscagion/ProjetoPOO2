package project.poo2.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.poo2.dto.EventDTO;
import project.poo2.dto.EventInsertDTO;
import project.poo2.dto.EventUpdateDTO;
import project.poo2.entities.Admin;
import project.poo2.entities.Event;
import project.poo2.repositories.AdminRepository;
import project.poo2.repositories.EventRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    public Page<EventDTO> getEvent(PageRequest pageRequest, String eventName, String eventDescription, String eventPlace, String eventStartDate_st){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate eventStartDate = LocalDate.parse(eventStartDate_st, formatter);
            Page<Event> list = eventRepository.findAll(pageRequest, eventName, eventDescription, eventPlace, eventStartDate);
            return list.map(e -> new EventDTO(e));
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "incorrect date use the format yyyy-MM-dd");
        }
    }

    public List<EventDTO> toDTOList(List<Event> list) {
        List<EventDTO> listDTO = new ArrayList<>();

        for (Event e : list) {
            EventDTO dto = new EventDTO(e.getId(), e.getName(), e.getDescription(),
                                        e.getStartDate(), e.getEndDate(), e.getStartTime(),
                                        e.getEndTime(), e.getEmailContact());
            listDTO.add(dto);
        }

        return listDTO;
    }

    public EventDTO getEventById(Long id) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        return new EventDTO(event);
    }

    public void delete(Long id){
        try{
            eventRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }

    public EventDTO insert(@Valid EventInsertDTO dto){
        Optional<Admin> op = adminRepository.findById(dto.getAdminId());
        Admin admin = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));

        if(dto.getEndDate().compareTo(dto.getStartDate()) < 0
            || (dto.getEndDate().compareTo(dto.getStartDate()) == 0
                && dto.getEndTime().compareTo(dto.getStartTime()) <= 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The end date is smaller or the same as the start date");
        } else {
            Event entity = new Event(dto);
            entity.setAdmin(admin);
            entity.setFreeTicketsSelled(0);
            entity.setPaidTicketsSelled(0);
            entity = eventRepository.save(entity);
            return new EventDTO(entity);
        }
    }


    public EventDTO update(Long id, @Valid EventUpdateDTO dto){
        try {
            Event entity = eventRepository.getOne(id);

            if(dto.getEndDate().compareTo(dto.getStartDate()) < 0
                || (dto.getEndDate().compareTo(dto.getStartDate()) == 0
                    && dto.getEndTime().compareTo(dto.getStartTime()) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The end date is smaller or the same as the start date");
            } else {
                entity.setName(dto.getName());
                entity.setDescription(dto.getDescription());
                entity.setEmailContact(dto.getEmailContact());
                entity.setStartDate(dto.getStartDate());
                entity.setEndDate(dto.getEndDate());
                entity.setStartTime(dto.getStartTime());
                entity.setEndTime(dto.getEndTime());
                entity = eventRepository.save(entity);
                return new EventDTO(entity);
            }
        }
        catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }
}

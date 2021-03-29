package project.poo2.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
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
import project.poo2.entities.Event;
import project.poo2.repositories.EventRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    
    public Page<EventDTO> getEvent(PageRequest pageRequest, String eventName, String eventDescription, String eventPlace, String eventStartDateTime_st){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime eventStartDateTime = LocalDateTime.parse(eventStartDateTime_st, formatter);
            Page<Event> list = eventRepository.findAll(pageRequest, eventName, eventDescription, eventPlace, eventStartDateTime);
            return list.map(e -> new EventDTO(e));
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "incorrect date use the format yyyy-MM-dd HH:mm:ss");
        }
    }

    public List<EventDTO> toDTOList(List<Event> list) {

        List<EventDTO> listDTO = new ArrayList<>();

        for (Event e : list) {
            EventDTO dto = new EventDTO(e.getId(),e.getName(),e.getDescription(),e.getPlace(),e.getStartDateTime(),e.getEndDateTime(),e.getEmailContact());
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

        if(dto.getEndDateTime().compareTo(dto.getStartDateTime()) <= 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the end date is smaller or the same as the start date");
        }else{
            Event entity = new Event(dto);
            entity = eventRepository.save(entity);
            return new EventDTO(entity);
        }
    }


    public EventDTO update(Long id,@Valid EventUpdateDTO dto){
        try{
          Event entity = eventRepository.getOne(id);
          entity.setName(dto.getName());
          entity = eventRepository.save(entity);
          return new EventDTO(entity);
        }
        catch(EntityNotFoundException ex){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }   
    }
}

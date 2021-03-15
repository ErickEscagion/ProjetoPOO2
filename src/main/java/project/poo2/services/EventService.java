package project.poo2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.poo2.dto.EventDTO;
import project.poo2.dto.EventInsertDTO;
import project.poo2.entities.Event;
import project.poo2.repositories.EventRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> getEvent(){
        List<Event> list = eventRepository.findAll();
        return toDTOList(list);
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
}

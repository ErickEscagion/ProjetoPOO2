package project.poo2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.poo2.dto.EventDTO;
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
            EventDTO dto = new EventDTO(e.getName(),e.getDescription(),e.getPlace(),e.getStartDateTime(),e.getEndDateTime(),e.getEmailContact());
            listDTO.add(dto);
        }

        return listDTO;
    }
}

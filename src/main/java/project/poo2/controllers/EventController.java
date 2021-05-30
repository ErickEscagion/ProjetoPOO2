package project.poo2.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import project.poo2.dto.EventDTO;
import project.poo2.dto.EventInsertDTO;
import project.poo2.dto.EventUpdateDTO;
import project.poo2.dto.PlaceDTO;
import project.poo2.dto.TicketDTO;
import project.poo2.dto.EventTicketDTO;
import project.poo2.dto.TicketInsertDTO;
import project.poo2.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvent(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
        @RequestParam(value = "name", defaultValue = "") String eventName,
        @RequestParam(value = "description", defaultValue = "") String eventDescription,
        @RequestParam(value = "place", defaultValue = "") String eventPlace,
        @RequestParam(value = "startDate", defaultValue = "2012-12-12") String eventStartDate
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<EventDTO> list = eventService.getEvent(pageRequest, eventName, eventDescription, eventPlace, eventStartDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        EventDTO dto = eventService.getEventById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{id}/tickets")
    public ResponseEntity<EventTicketDTO> getEventTickets(
        @PathVariable Long id,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
        @RequestParam(value = "type", defaultValue = "") String ticketType
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        EventTicketDTO tickets = eventService.getEventTickets(pageRequest, id, ticketType);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("{id}/tickets")
    public ResponseEntity<TicketDTO> insertTicket(@PathVariable Long id, @Valid @RequestBody TicketInsertDTO ticketInsertDTO) {
        TicketDTO dto = eventService.insertTicket(id, ticketInsertDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}/tickets/{ticketId}")
    public ResponseEntity<Void> removeTicket(@PathVariable Long id, @PathVariable Long ticketId) {
        eventService.removeTicket(id, ticketId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/places")
    public ResponseEntity<Page<PlaceDTO>> getEventPlaces(
        @PathVariable Long id,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
        @RequestParam(value = "name", defaultValue = "") String placeName,
        @RequestParam(value = "address", defaultValue = "") String placeAddress
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<PlaceDTO> places = eventService.getEventPlaces(pageRequest, id, placeName, placeAddress);
        return ResponseEntity.ok(places);
    }

    @PostMapping("/{id}/places/{placeId}")
    public ResponseEntity<PlaceDTO> insertPlace(@PathVariable Long id, @PathVariable Long placeId) {
        PlaceDTO dto = eventService.insertPlace(id, placeId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}/places/{placeId}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id, @PathVariable Long placeId) {
        eventService.deletePlace(id, placeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		eventService.delete(id); 
		return ResponseEntity.noContent().build();
	}

    @PostMapping
	public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventInsertDTO insertDto) {
		EventDTO dto = eventService.insert(insertDto); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

    @PutMapping("{id}")
	public ResponseEntity<EventDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody EventUpdateDTO updateDto){
		EventDTO dto = eventService.update(id, updateDto); 
		return ResponseEntity.ok().body(dto);
	}



}

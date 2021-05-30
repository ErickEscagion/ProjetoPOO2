package project.poo2.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.poo2.dto.AttendDTO;
import project.poo2.dto.EventDTO;
import project.poo2.dto.EventInsertDTO;
import project.poo2.dto.EventUpdateDTO;
import project.poo2.dto.PlaceDTO;
import project.poo2.dto.TicketDTO;
import project.poo2.dto.EventTicketDTO;
import project.poo2.dto.TicketInsertDTO;
import project.poo2.entities.Admin;
import project.poo2.entities.Attend;
import project.poo2.entities.Event;
import project.poo2.entities.Place;
import project.poo2.entities.Ticket;
import project.poo2.entities.TicketType;
import project.poo2.repositories.AdminRepository;
import project.poo2.repositories.AttendRepository;
import project.poo2.repositories.EventRepository;
import project.poo2.repositories.PlaceRepository;
import project.poo2.repositories.TicketRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AttendRepository attendRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PlaceService placeService;
    
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
            EventDTO dto = new EventDTO(e);
            listDTO.add(dto);
        }

        return listDTO;
    }

    public EventDTO getEventById(Long id) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        return new EventDTO(event);
    }

    public EventTicketDTO getEventTickets(PageRequest pageRequest, Long id, String ticketType) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        TicketType type = null;

        if (ticketType.equalsIgnoreCase("free") || ticketType.equalsIgnoreCase("paid")) {
            type = TicketType.valueOf(ticketType.toUpperCase());
        } else if (ticketType != null && ticketType.length() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type must be 'free' or 'paid'");
        }

        Page<Ticket> tickets = ticketRepository.findAllByEventId(pageRequest, id, type);
        Page<TicketDTO> pageResult = tickets.map(t -> new TicketDTO(t));

        return new EventTicketDTO(pageResult, event);
    }

    public TicketDTO insertTicket(Long id, TicketInsertDTO insertDTO) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        if (event.getEndDate().compareTo(nowDate) < 0
            || (event.getEndDate().compareTo(nowDate) == 0 && event.getEndTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already finished");
        }

        if (event.getStartDate().compareTo(nowDate) < 0
            || (event.getStartDate().compareTo(nowDate) == 0 && event.getStartTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already began");
        }

        Optional<Attend> opAttend = attendRepository.findById(insertDTO.getAttendId());
        Attend attend = opAttend.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found"));

        Ticket ticket = new Ticket(
            insertDTO.getType(),
            Instant.now(),
            insertDTO.getType() == TicketType.FREE ? 0 : event.getPriceTicket(),
            attend,
            event
        );

        if (ticket.getType() == TicketType.FREE && event.getAmountFreeTickets() - event.getFreeTicketsSelled() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has no more free tickets");
        } else if (ticket.getType() == TicketType.PAID && event.getAmountPaidTickets() - event.getPaidTicketsSelled() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has no more paid tickets");
        }

        if (attend.getBalance() < ticket.getPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This attend cannot afford this event ticket");
        }

        event.addTicket(ticket);
        attend.addTicket(ticket);

        eventRepository.save(event);
        attendRepository.save(attend);
        Ticket newTicket = ticketRepository.save(ticket);

        return new TicketDTO(newTicket, new AttendDTO(attend), new EventDTO(event));
    }

    public void removeTicket(long id, long ticketId) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        if (event.getEndDate().compareTo(nowDate) < 0
            || (event.getEndDate().compareTo(nowDate) == 0 && event.getEndTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already finished");
        }
        
        if (event.getStartDate().compareTo(nowDate) < 0
            || (event.getStartDate().compareTo(nowDate) == 0 && event.getStartTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already began");
        }

        List<Ticket> tickets = event.getTickets();

        if (tickets.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This event has no tickets");
        }

        List<Ticket> filteredTickets = tickets.stream().filter(t -> t.getId() == ticketId).collect(Collectors.toList());

        if (filteredTickets.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }

        Ticket ticket = filteredTickets.get(0);

        Optional<Attend> opAttend = attendRepository.findById(ticket.getAttend().getId());
        Attend attend = opAttend.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found"));

        event.removeTicket(ticket);
        attend.removeTicket(ticket);

        eventRepository.save(event);
        attendRepository.save(attend);
        ticketRepository.deleteById(ticketId);
    }

    public Page<PlaceDTO> getEventPlaces(PageRequest pageRequest, long id, String placeName, String placeAddress) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        Page<Place> places = placeRepository.findAllByEventId(pageRequest, event.getId(), placeName, placeAddress);

        return places.map(p -> new PlaceDTO(p));
    }

    public PlaceDTO insertPlace(long id, long placeId) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        if (event.getEndDate().compareTo(nowDate) < 0
            || (event.getEndDate().compareTo(nowDate) == 0 && event.getEndTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already finished");
        }
        
        if (event.getStartDate().compareTo(nowDate) < 0
            || (event.getStartDate().compareTo(nowDate) == 0 && event.getStartTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already began");
        }

        List<Place> eventPlaces = event.getPlaces();

        if (eventPlaces.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This event has no places");
        }

        eventPlaces.forEach(p -> {
            if (p.getId() == placeId) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event already has this place");
            }
        });

        Optional<Place> opPlace = placeRepository.findById(placeId);
        Place place = opPlace.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        if (!placeService.isAvailable(place, event)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The place is not available at this time");
        }

        event.addPlace(place);
        eventRepository.save(event);

        return new PlaceDTO(place);
    }

    public void deletePlace(long id, long placeId) {
        Optional<Event> op = eventRepository.findById(id);
        Event event = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        if (event.getEndDate().compareTo(nowDate) < 0
            || (event.getEndDate().compareTo(nowDate) == 0 && event.getEndTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already finished");
        }
        
        if (event.getStartDate().compareTo(nowDate) < 0
            || (event.getStartDate().compareTo(nowDate) == 0 && event.getStartTime().compareTo(nowTime) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already began");
        }

        List<Place> eventPlaces = event.getPlaces();

        if (eventPlaces.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This event has no places");
        } else if (eventPlaces.size() == 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An event must have at least one place");
        }

        Place place = null;

        for (Place p : eventPlaces) {
            if (p.getId() == placeId) {
                place = p;
                break;
            }
        }

        if (place == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This event does not have any place with the id " + placeId);
        }

        event.removePlace(place);
        eventRepository.save(event);
    }

    public void delete(Long id){
        try {
            Event entity = eventRepository.getOne(id);

            if (entity.getFreeTicketsSelled() > 0 || entity.getPaidTicketsSelled() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event already sold tickets");
            }

            eventRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }

    public EventDTO insert(@Valid EventInsertDTO dto){
        Optional<Admin> op = adminRepository.findById(dto.getAdminId());
        Admin admin = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));

        Optional<Place> opPlace = placeRepository.findById(dto.getPlaceId());
        Place place = opPlace.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        if(dto.getEndDate().compareTo(dto.getStartDate()) < 0
            || (dto.getEndDate().compareTo(dto.getStartDate()) == 0
                && dto.getEndTime().compareTo(dto.getStartTime()) <= 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The end date is smaller or the same as the start date");
        } else {
            Event entity = new Event(dto);

            if (!placeService.isAvailable(place, entity)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The place is not available at this time");
            }

            entity.setAdmin(admin);
            entity.addPlace(place);
            entity.setFreeTicketsSelled(0);
            entity.setPaidTicketsSelled(0);
            entity = eventRepository.save(entity);
            return new EventDTO(entity);
        }
    }


    public EventDTO update(Long id, @Valid EventUpdateDTO dto) {
        try {
            Event entity = eventRepository.getOne(id);

            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();

            if (entity.getEndDate().compareTo(nowDate) < 0
                || (entity.getEndDate().compareTo(nowDate) == 0 && entity.getEndTime().compareTo(nowTime) <= 0)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This event has already finished");
            } else if(dto.getEndDate().compareTo(dto.getStartDate()) < 0
                || (dto.getEndDate().compareTo(dto.getStartDate()) == 0
                    && dto.getEndTime().compareTo(dto.getStartTime()) <= 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The end date is smaller or the same as the start date");
            } else {
                List<Place> places = entity.getPlaces();

                for (Place place : places) {
                    if (!placeService.isAvailable(place, entity)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The place " + place.getName() + " is not available at this time");
                    }
                }

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

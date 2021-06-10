package project.poo2.services;

import java.time.LocalDate;
import java.time.LocalTime;
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

import project.poo2.dto.PlaceDTO;
import project.poo2.entities.Event;
import project.poo2.entities.Place;
import project.poo2.repositories.PlaceRepository;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    
    public Page<PlaceDTO> getPlace(PageRequest pageRequest, String placeName, String placeAddress){
        try {
            Page<Place> list = placeRepository.findAll(pageRequest, placeName, placeAddress);
            return list.map(p -> new PlaceDTO(p));
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
        }
    }

    public PlaceDTO getPlaceById(Long id) {
        Optional<Place> op = placeRepository.findById(id);
        Place place = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        return new PlaceDTO(place);
    }

    public void delete(Long id){
        try{
            Place entity = placeRepository.getOne(id);

            if (entity.getEvents().size() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This place has already been used by an event");
            }

            placeRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }
    }

    public PlaceDTO insert(@Valid Place place){
        Place entity = new Place(place);
        entity = placeRepository.save(entity);
        return new PlaceDTO(entity);
    }

    public PlaceDTO update(Long id,@Valid Place place){
        try{
            Place entity = placeRepository.getOne(id);
            entity.setName(place.getName());
            entity.setAddress(place.getAddress());
            entity = placeRepository.save(entity);
            return new PlaceDTO(entity);
        }
        catch(EntityNotFoundException ex){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }   
    }

    public boolean isAvailable(Place place, Event event) {
        List<Event> events = place.getEvents();

        LocalDate startDate = event.getStartDate();
        LocalDate endDate = event.getEndDate();
        LocalTime startTime = event.getStartTime();
        LocalTime endTime = event.getEndTime();

        List<Boolean> results = new ArrayList<>();

        for (Event e : events) {
            if (e.getId() == event.getId()) {
                results.add(true);
            } else if(compareDate(e.getStartDate(), endDate) == 0 && compareDate(e.getEndDate(), startDate) == 0) {
                if ((compareTime(e.getStartTime(), startTime) >= 0 && compareTime(e.getStartTime(), endTime) < 0)
                || (compareTime(e.getEndTime(), endTime) <= 0 && compareTime(e.getEndTime(), startTime) > 0)
                || (compareTime(e.getStartTime(), startTime) < 0 && compareTime(e.getEndTime(), endTime) > 0)) {
                    return false;
                }
            } else if (compareDate(e.getStartDate(), startDate) <= 0 && compareDate(e.getEndDate(), endDate) >= 0) {
                return false;
            } else if (compareDate(e.getStartDate(), endDate) == 0 && compareDate(e.getEndDate(), endDate) >= 0) {
                if (compareTime(e.getStartTime(), endTime) < 0) {
                    return false;
                }
                results.add(true);
            } else if (compareDate(e.getEndDate(), startDate) == 0 && compareDate(e.getStartDate(), startDate) <= 0) {
                if (compareTime(e.getEndTime(), startTime) > 0) {
                    return false;
                }
                results.add(true);
            } else if ((compareDate(e.getStartDate(), startDate) >= 0 && compareDate(e.getStartDate(), endDate) < 0)
                || (compareDate(e.getEndDate(), endDate) <= 0 && compareDate(e.getEndDate(), startDate) > 0)
                || (compareDate(e.getStartDate(), startDate) < 0 && compareDate(e.getEndDate(), endDate) > 0)) {
                return false;
            }
        }
        
        return !results.contains(false);
    }

    private int compareDate(LocalDate first, LocalDate second) {
        return first.compareTo(second);
    }

    private int compareTime(LocalTime first, LocalTime second) {
        return first.compareTo(second);
    }
}

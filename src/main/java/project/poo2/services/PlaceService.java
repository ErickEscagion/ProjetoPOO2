package project.poo2.services;

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
}

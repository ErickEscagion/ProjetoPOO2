package project.poo2.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.poo2.dto.AttendDTO;
import project.poo2.dto.AttendUpdateDTO;
import project.poo2.entities.Attend;
import project.poo2.entities.BaseUser;
import project.poo2.repositories.AttendRepository;

@Service
public class AttendService {

    @Autowired
    private AttendRepository attendRepository;

    public Page<AttendDTO> getAttend(PageRequest pageRequest, String attendName, String attendEmail, Double attendBalance){
        try {
            Page<Attend> list = attendRepository.findAll(pageRequest, attendName, attendEmail, attendBalance);
            return list.map(a -> new AttendDTO(a));
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend search gave error");
        }
    }

    public AttendDTO getAttendById(Long id) {
        Optional<Attend> op = attendRepository.findById(id);
        Attend attend = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found"));

        return new AttendDTO(attend);
    }

    public AttendDTO insert(AttendDTO attendDTO) {
        BaseUser user = attendRepository.findByEmail(attendDTO.getEmail());

        if (user != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This e-mail already exists");
        }

        Attend entity = new Attend(attendDTO);
        entity = attendRepository.save(entity);
        return new AttendDTO(entity);
    }

    public AttendDTO update(Long id, AttendUpdateDTO dto){
        try {
            Attend entity = attendRepository.getOne(id);

            BaseUser user = attendRepository.findByEmail(dto.getEmail());

            if (user != null && user.getEmail() != entity.getEmail()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This e-mail already exists");
            }

            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity = attendRepository.save(entity);
            return new AttendDTO(entity);
        }
        catch(EntityNotFoundException ex){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }   
    }

    public void delete(Long id){
        try{
            attendRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
        }
    }
}

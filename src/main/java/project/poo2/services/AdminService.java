package project.poo2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import project.poo2.dto.AdminDTO;
import project.poo2.entities.Admin;
import project.poo2.repositories.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Page<AdminDTO> getAdmin(PageRequest pageRequest, String adminName, String adminEmail, String adminPhoneNumber){
        try{
            Page<Admin> list = adminRepository.findAll(pageRequest, adminName, adminEmail, adminPhoneNumber);
            return list.map(a -> new AdminDTO(a));
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrator search gave error");
        }
    }

    public AdminDTO getAdminById(Long id) {
        Optional<Admin> op = adminRepository.findById(id);
        Admin admin = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));

        return new AdminDTO(admin);
    }

    public void delete(Long id){
        try{
            adminRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrator not found");
        }
    }
}

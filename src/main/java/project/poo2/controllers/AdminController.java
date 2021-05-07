package project.poo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.poo2.dto.AdminDTO;
import project.poo2.services.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<Page<AdminDTO>> getAdmin(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
        @RequestParam(value = "name", defaultValue = "") String adminName,
        @RequestParam(value = "email", defaultValue = "") String adminEmail,
        @RequestParam(value = "phoneNumber", defaultValue = "") String adminPhoneNumber
    ){
        
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        Page<AdminDTO> list = adminService.getAdmin(pageRequest, adminName, adminEmail,  adminPhoneNumber);
        return ResponseEntity.ok(list);
    }


    @GetMapping("{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        AdminDTO dto = adminService.getAdminById(id);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		adminService.delete(id); 
		return ResponseEntity.noContent().build();
	}
    
    
}

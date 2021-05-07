package project.poo2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.poo2.entities.Admin;

@Repository

public interface AdminRepository extends JpaRepository <Admin,Long>{
    @Query("SELECT a FROM Admin a " +
    "WHERE" +
    "(LOWER(a.name)        LIKE  LOWER(CONCAT('%', :adminName,          '%'))) AND " +
    "(LOWER(a.email)       LIKE  LOWER(CONCAT('%', :adminEmail,         '%'))) AND " +
    "(LOWER(a.phoneNumber) LIKE  LOWER(CONCAT('%', :adminPhoneNumber,   '%'))) "
    )
    public Page <Admin> findAll(Pageable pageRequest, String adminName, String adminEmail, String adminPhoneNumber);
}
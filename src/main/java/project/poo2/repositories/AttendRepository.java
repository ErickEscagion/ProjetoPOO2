package project.poo2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.poo2.entities.Attend;
import project.poo2.entities.BaseUser;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {
    @Query("SELECT a FROM Attend a " +
    "WHERE" +
    "(LOWER(a.name)        LIKE  LOWER(CONCAT('%', :attendName,          '%'))) AND " +
    "(LOWER(a.email)       LIKE  LOWER(CONCAT('%', :attendEmail,         '%'))) AND " +
    "(:attendBalance = null OR a.balance = :attendBalance)"
    )
    public Page<Attend> findAll(Pageable pageRequest, String attendName, String attendEmail, Double attendBalance);

    @Query("SELECT u FROM BaseUser u " +
    "WHERE LOWER(u.email) = LOWER(:email)")
    public BaseUser findByEmail(String email);
}

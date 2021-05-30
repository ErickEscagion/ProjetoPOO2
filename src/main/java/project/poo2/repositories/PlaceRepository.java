package project.poo2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.poo2.entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository <Place,Long>{
    @Query("SELECT p FROM Place p " +
    "WHERE " +
    "(LOWER(p.name)     LIKE LOWER(CONCAT('%', :placeName,'%'))) AND " +
    "(LOWER(p.address)  LIKE LOWER(CONCAT('%', :placeAddress,'%'))) "
    )
    public Page <Place> findAll(Pageable pageRequest, String placeName, String placeAddress);

    @Query("SELECT p FROM Place p " +
    "LEFT JOIN p.events e " +
    "WHERE " +
    "(e.id = :eventId) AND " +
    "(LOWER(p.name)     LIKE LOWER(CONCAT('%', :placeName,'%'))) AND " +
    "(LOWER(p.address)  LIKE LOWER(CONCAT('%', :placeAddress,'%'))) "
    )
    public Page <Place> findAllByEventId(Pageable pageRequest, Long eventId, String placeName, String placeAddress);
}

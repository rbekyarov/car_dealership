package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Picture;

import java.time.LocalDate;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Transactional
    @Modifying
    @Query("update Picture as p SET p.name = :name, p.author.id=:editAuthorId,p.dateCreate=:dateEdit where p.id=:id ")
    void editPicture(@Param("name") String name,
                   @Param("id") Long id ,
                   @Param("editAuthorId") Long editAuthorId,
                   @Param("dateEdit") LocalDate dateEdit);
}

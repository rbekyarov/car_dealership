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
    @Query("update Picture as p SET p.name = :name, p.editUser.id=:editUserId,p.dateEdite=:dateEdit\n where p.id=:id ")
    void editPicture(@Param("name") String name,
                   @Param("id") Long id ,
                     @Param("editUserId") Long editUserId,
                     @Param("dateEdit") LocalDate dateEdit);
    @Transactional
    @Modifying
    @Query("update Picture as p SET p.car.id = :carId  where p.id=:id ")
    void updatePicturesTableFieldsCarId( @Param("id") Long id ,@Param("carId") Long carId);
}

package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Model;

import java.time.LocalDate;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {


    @Transactional
    @Modifying
    @Query("update Model as m SET m.name = :name, m.brand.id = :brandId, m.author.id=:editAuthorId, m.dateCreate=:dateEdit where m.id=:id ")
    void editModel(@Param("name") String name,
                   @Param("brandId") Long brandId ,
                   @Param("id") Long id ,
                   @Param("editAuthorId") Long editAuthorId,
                   @Param("dateEdit") LocalDate dateEdit);
}

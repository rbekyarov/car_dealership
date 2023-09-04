package rbekyarov.car_dealership.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Brand;

import java.time.LocalDate;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Transactional
    @Modifying
    @Query("update Brand as b SET b.name = :name, b.author.id=:editAuthorId,b.dateCreate=:dateEdit where b.id=:id ")
    void editBrand(@Param("name") String name,
                      @Param("id") Long id ,
                      @Param("editAuthorId") Long editAuthorId,
                      @Param("dateEdit") LocalDate dateEdit);

}

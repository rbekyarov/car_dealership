package rbekyarov.car_dealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbekyarov.car_dealership.models.entity.Model;
@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
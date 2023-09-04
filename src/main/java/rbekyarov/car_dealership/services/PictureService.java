package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.BrandDTO;
import rbekyarov.car_dealership.models.dto.PictureDTO;
import rbekyarov.car_dealership.models.entity.Brand;
import rbekyarov.car_dealership.models.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    void addPicture(PictureDTO pictureDTO, HttpSession session);

    void removePictureById(Long id);

    Optional<Picture> findById(Long id);

    void editPicture(String name, Long id, HttpSession session);
}

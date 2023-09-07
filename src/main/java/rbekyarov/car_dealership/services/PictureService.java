package rbekyarov.car_dealership.services;

import jakarta.servlet.http.HttpSession;
import rbekyarov.car_dealership.models.dto.PictureDTO;
import rbekyarov.car_dealership.models.entity.Picture;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PictureService {
    List<Picture> findAllPictures();
    void addPicture(PictureDTO pictureDTO, HttpSession session);

    void removePictureById(Long id);

    Optional<Picture> findById(Long id);

    void editPicture(String name, Long id, HttpSession session);

    void updatePicturesTableFieldsCarId(Set<Picture> pictures, Long carId);

    List<Picture> getAllPicturesByCar(Long id);
}

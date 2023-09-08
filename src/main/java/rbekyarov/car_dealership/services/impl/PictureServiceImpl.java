package rbekyarov.car_dealership.services.impl;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.car_dealership.models.dto.PictureDTO;
import rbekyarov.car_dealership.models.entity.Picture;
import rbekyarov.car_dealership.models.entity.User;
import rbekyarov.car_dealership.repository.PictureRepository;
import rbekyarov.car_dealership.services.PictureService;
import rbekyarov.car_dealership.services.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl( ModelMapper modelMapper, UserService userService,
                              PictureRepository pictureRepository) {

        this.modelMapper = modelMapper;
        this.userService = userService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> findAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public void addPicture(PictureDTO pictureDTO, HttpSession session) {
        Picture picture = modelMapper.map(pictureDTO, Picture.class);
        //Change picture name
        String name = changePictureName(pictureDTO.getName());
        picture.setName(name);
        //get and set Author
        //picture.setAuthor(userService.getAuthorFromSession(session));
        picture.setAuthor(userService.findById(1L).get());
        // set dateCreated
        picture.setDateCreate(LocalDate.now());
        pictureRepository.saveAndFlush(picture);
    }



    @Override
    public void removePictureById(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void editPicture(String name, Long id, HttpSession session) {
        User editUser = userService.getAuthorFromSession(session);
        Long editUserId = editUser.getId();

        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        pictureRepository.editPicture(name, id,editUserId, dateEdit);
    }

    @Override
    public void updatePicturesTableFieldsCarId(Set<Picture> pictures, Long carId) {
        for (Picture picture : pictures) {

            pictureRepository.updatePicturesTableFieldsCarId(picture.getId(), carId);
        }

    }

    @Override
    public List<Picture> getAllPicturesByCar(Long id) {
        return pictureRepository.findAllById(Collections.singleton(id)).stream().toList();
    }


    private String changePictureName(String name) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_");
        String prefix = currentDateTime.format(formatter);
        return prefix+name;
    }
}

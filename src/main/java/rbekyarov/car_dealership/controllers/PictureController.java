package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.PictureDTO;
import rbekyarov.car_dealership.models.entity.Picture;
import rbekyarov.car_dealership.services.PictureService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping
    public ResponseEntity<?> getAllPictures() {
        List<Picture> allPictures = pictureService.findAllPictures();
        return new ResponseEntity<>(allPictures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPictureById(@PathVariable Long id) {
        Picture picture = pictureService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Picture not exist with id: " + id));
        return new ResponseEntity<>(picture, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPicture(@RequestBody @Valid PictureDTO pictureDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            pictureService.addPicture(pictureDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePicture(@PathVariable Long id, @RequestBody @Valid PictureDTO pictureDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            pictureService.editPicture(pictureDTO.getName(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePicture(@PathVariable Long id) {
        pictureService.removePictureById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

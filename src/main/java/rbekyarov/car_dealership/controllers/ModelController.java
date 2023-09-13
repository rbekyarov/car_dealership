package rbekyarov.car_dealership.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rbekyarov.car_dealership.exception.ResourceNotFoundException;
import rbekyarov.car_dealership.models.dto.ModelDTO;
import rbekyarov.car_dealership.models.entity.Model;
import rbekyarov.car_dealership.services.ModelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping
    public ResponseEntity<?> getAllModels() {
        List<Model> allModels = modelService.findAllModels();
        return new ResponseEntity<>(allModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModelById(@PathVariable Long id) {
        Model model = modelService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Model not exist with id: " + id));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createModel(@RequestBody @Valid ModelDTO modelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            modelService.addModel(modelDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateModel(@PathVariable Long id, @RequestBody @Valid ModelDTO modelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            String name = modelDTO.getName();
            Long brandId = modelDTO.getBrandId();
            modelService.editModel(name, brandId, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteModel(@PathVariable Long id) {
        modelService.removeModelById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

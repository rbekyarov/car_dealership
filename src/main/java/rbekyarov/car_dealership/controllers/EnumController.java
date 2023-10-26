package rbekyarov.car_dealership.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rbekyarov.car_dealership.models.entity.enums.*;

@CrossOrigin(origins = "*")
@RestController

public class EnumController {
    @GetMapping("/api/enumTransmission")
    public String getTransmissionValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Transmision.values());
        return json;
    }
    @GetMapping("/api/enumFuelType")
    public String getFuelTypeValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(FuelType.values());
        return json;
    }
    @GetMapping("/api/enumColor")
    public String getColorValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Color.values());
        return json;
    }
    @GetMapping("/api/enumDoorCount")
    public String getDoorValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(DoorCount.values());
        return json;
    }
    @GetMapping("/api/enumConditionCar")
    public String getConditionValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ConditionCar.values());
        return json;
    }
    @GetMapping("/api/enumEurostandard")
    public String getEurostandardValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Eurostandard.values());
        return json;
    }
    @GetMapping("/api/enumCategory")
    public String getCategoryValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Category.values());
        return json;
    }
    @GetMapping("/api/enumAutoStartStop")
    public String getAutoStartStopValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(AutoStartStop.values());
        return json;
    }

    @GetMapping("/api/enumAlarm")
    public String getAlarmValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Alarm.values());
        return json;
    }
    @GetMapping("/api/enumMetallic")
    public String getMetallicValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Metallic.values());
        return json;
    }
    @GetMapping("/api/enumLeatherSalon")
    public String getLeatherSalonValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(LeatherSalon.values());
        return json;
    }
    @GetMapping("/api/enumHalogenHeadlights")
    public String getHalogenHeadlightsValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(HalogenHeadlights.values());
        return json;
    }
    @GetMapping("/api/enumServiceBook")
    public String getServiceBookValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ServiceBook.values());
        return json;
    }
    @GetMapping("/api/enumAirbags")
    public String getAirbagsValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Airbags.values());
        return json;
    }
    @GetMapping("/api/enumElMirrors")
    public String getElMirrorsValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ElMirrors.values());
        return json;
    }
    @GetMapping("/api/enumElWindows")
    public String getElWindowsValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ElWindows.values());
        return json;
    }
    @GetMapping("/api/enumClimatic")
    public String getClimaticValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Climatic.values());
        return json;
    }
    @GetMapping("/api/enumNavigation")
    public String getNavigationValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Navigation.values());
        return json;
    }
    @GetMapping("/api/enumParktronik")
    public String getParktronikValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Parktronik.values());
        return json;
    }
    @GetMapping("/api/enumStatusAvailable")
    public String getAvailableValues() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(StatusAvailable.values());
        return json;
    }
}

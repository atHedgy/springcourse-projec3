package kz.hedgy.thirdproject.controllers;

import kz.hedgy.thirdproject.dto.SensorDTO;
import kz.hedgy.thirdproject.models.SensorEntity;
import kz.hedgy.thirdproject.services.SensorService;
import kz.hedgy.thirdproject.utils.SensorValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/*********************
 * @CREATED: 02.09.2022
 * @AUTHOR: Hedgy
 **********************/

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/sensors")

public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@Valid @RequestBody SensorDTO sensorDTO,
                                                     BindingResult bindingResult) throws MethodArgumentNotValidException, NoSuchMethodException{
        SensorEntity sensor = convertDtoToModel(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors())
            throw new MethodArgumentNotValidException(null, bindingResult);

        sensorService.registerSensor(sensor);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    public List<SensorDTO> getSensors() {
        return sensorService.getAll().stream().map(this::convertModelToDto).collect(Collectors.toList());
    }

    private SensorEntity convertDtoToModel(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, SensorEntity.class);
    }

    private SensorDTO convertModelToDto(SensorEntity sensorEntity) {
        return modelMapper.map(sensorEntity, SensorDTO.class);
    }
}

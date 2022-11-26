package kz.hedgy.thirdproject.controllers;

import kz.hedgy.thirdproject.dto.MeasurementDTO;
import kz.hedgy.thirdproject.models.MeasurementEntity;
import kz.hedgy.thirdproject.services.MeasurementService;
import kz.hedgy.thirdproject.utils.MeasurementValidator;
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
 * @CREATED: 10.09.2022
 * @AUTHOR: Hedgy
 **********************/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@Valid @RequestBody MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) throws MethodArgumentNotValidException, NoSuchMethodException {
        MeasurementEntity measurement = convertDtoToModel(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors())
            throw new MethodArgumentNotValidException(null, bindingResult);

        measurementService.addMeasuring(measurement);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.getAll().stream().map(this::convertModelToDto).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays() {
        return measurementService.getRainingCount();
    }

    private MeasurementEntity convertDtoToModel(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, MeasurementEntity.class);
    }

    private MeasurementDTO convertModelToDto(MeasurementEntity measurementEntity) {
        return modelMapper.map(measurementEntity, MeasurementDTO.class);
    }
}

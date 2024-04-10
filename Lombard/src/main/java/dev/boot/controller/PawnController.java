package dev.boot.controller;



import dev.boot.dto.CarDTO;
import dev.boot.dto.JewelryDTO;
import dev.boot.dto.PawnItemDTO;
import dev.boot.dto.TechnologyDTO;
import dev.boot.service.CarService;
import dev.boot.service.ItemService;
import dev.boot.service.JewelryService;
import dev.boot.service.TechnologyService;
import dev.omedia.boot.dto.*;
import dev.omedia.boot.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pawn")
public class PawnController {
    private final CarService carService;
    private final JewelryService jewelryService;
    private final TechnologyService technologyService;
    private final ItemService itemService;


    @PostMapping("/addCar/{branch_id}")
    public ResponseEntity<CarDTO> addCar(@PathVariable long branch_id, @RequestBody CarDTO carDTO)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(branch_id,carDTO));
    }
    @PostMapping("/addJewelery/{branch_id}")
    public ResponseEntity<JewelryDTO> addJewelery(@PathVariable long branch_id, @RequestBody JewelryDTO jewelryDTO)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(jewelryService.save(branch_id,jewelryDTO));
    }
    @PostMapping("/addTechnology/{branch_id}")
    public ResponseEntity<TechnologyDTO> addTechnology(@PathVariable long branch_id, @RequestBody TechnologyDTO technologyDTO)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(technologyService.save(branch_id,technologyDTO));
    }

    @GetMapping("/getAll/{branch_id}")
    public List<PawnItemDTO> getAllItems(@PathVariable long branch_id ){
        return  itemService.findAll(branch_id);
    }

}

package dev.boot.service;

import dev.boot.domain.Branch;
import dev.boot.domain.Car;
import dev.boot.dto.CarDTO;
import dev.boot.repository.AccountRepository;
import dev.boot.repository.BranchRepository;
import dev.boot.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CarService {
    private final CarRepository repository;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final AccountRepository accountRepository;

    public CarDTO save(Long id, CarDTO carDTO) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find branch with id " + id));
        Car vehicle = repository.save(modelMapper.map(carDTO, Car.class));

        branch.getItems().add(vehicle);
        vehicle.setBranch(branch);
        branchRepository.save(branch);
        repository.save(vehicle);
        accountRepository.save(vehicle.createAccount());
        return modelMapper.map(vehicle, CarDTO.class);

    }



}

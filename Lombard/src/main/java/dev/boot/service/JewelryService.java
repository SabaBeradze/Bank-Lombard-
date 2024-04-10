package dev.boot.service;

import dev.boot.domain.Branch;
import dev.boot.domain.Jewelry;
import dev.boot.dto.JewelryDTO;
import dev.boot.repository.AccountRepository;
import dev.boot.repository.BranchRepository;
import dev.boot.repository.JeweleryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class JewelryService {
    private final JeweleryRepository repository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;


    public JewelryDTO save(Long id, JewelryDTO jewelryDTO) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find branch with id " + id));
        Jewelry jewelry = repository.save(modelMapper.map(jewelryDTO, Jewelry.class));

        branch.getItems().add(jewelry);
        jewelry.setBranch(branch);
        branchRepository.save(branch);
        repository.save(jewelry);

        accountRepository.save(jewelry.createAccount());
        return modelMapper.map(jewelry, JewelryDTO.class);

    }


}

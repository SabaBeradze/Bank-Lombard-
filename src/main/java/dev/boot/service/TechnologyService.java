package dev.boot.service;


import dev.boot.domain.Branch;
import dev.boot.domain.Technology;
import dev.boot.dto.TechnologyDTO;
import dev.boot.repository.AccountRepository;
import dev.boot.repository.BranchRepository;
import dev.boot.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository repository;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final AccountRepository accountRepository;

    public TechnologyDTO save(Long id, TechnologyDTO technologyDTO) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find branch with id " + id));
        Technology technology = repository.save(modelMapper.map(technologyDTO, Technology.class));

        branch.getItems().add(technology);
        technology.setBranch(branch);
        branchRepository.save(branch);
        repository.save(technology);

        accountRepository.save(technology.createAccount());
        return modelMapper.map(technology, TechnologyDTO.class);
    }

}

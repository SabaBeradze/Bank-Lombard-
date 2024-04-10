package dev.boot.service;

import dev.boot.domain.Branch;
import dev.boot.dto.BranchDTO;
import dev.boot.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public BranchDTO save(BranchDTO entity) {
        Branch branch = branchRepository.save(modelMapper.map(entity,Branch.class));
        return modelMapper.map(branch, BranchDTO.class);
    }

    @Cacheable(cacheNames = "branch",key = "#aLong")
    public BranchDTO findById(Long aLong) {
        Branch branch = branchRepository.findById(aLong)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find branch with id " + aLong));

        return modelMapper.map(branch, BranchDTO.class);
    }
    @Cacheable("branch")
    public List<BranchDTO> findAll() {
        return StreamSupport.stream(branchRepository.findAll().spliterator(), false)
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }

}
